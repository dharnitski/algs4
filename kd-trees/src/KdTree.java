import java.io.Console;

import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

	private class Node {

		private Point2D p; // the point
		private RectHV rect; // the axis-aligned rectangle corresponding to this
								// node
		private Node lb; // the left/bottom subtree
		private Node rt; // the right/top subtree

		Node(Point2D p) {
			this.p = p;
		}

		private boolean type;

		boolean vertical() {
			return !type;
		}

		boolean horizontal() {
			return type;
		}

		Node addPoint(Point2D newP) {
			if (this.p.equals(newP))
				return null;

			if ((vertical() && newP.x() < p.x()) || (horizontal() && newP.y() < p.y())) {
				if (lb == null) {
					lb = new Node(newP);
					lb.type = !this.type;
					setRectangle(this, lb);
					return lb;
				} else {
					return lb.addPoint(newP);
				}
			} else {
				if (rt == null) {
					rt = new Node(newP);
					rt.type = !this.type;
					setRectangle(this, rt);
					return rt;
				} else {
					return rt.addPoint(newP);
				}
			}
		}

		void setRectangle(Node parent, Node node) {
			if (parent.vertical()) {
				if (node.p.x() < parent.p.x()) {
					node.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.p.x(), parent.rect.ymax());
				} else {
					node.rect = new RectHV(parent.p.x(), parent.rect.ymin(), parent.rect.xmax(), parent.rect.ymax());
				}
			}
			else
			{
				if (node.p.y() < parent.p.y()) {
					node.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.p.x(), parent.p.y());
				} else {
					node.rect = new RectHV(parent.rect.xmin(), parent.p.y(), parent.rect.xmax(), parent.rect.ymax());
				}
			}
		}

		public String toString() {
			String alighnment;
			if (this.vertical())
				alighnment = " | ";
			else
				alighnment = " - ";

			return p.toString() + alighnment;
		}

		void draw() {

			p.draw();

			if (lb != null)
				lb.draw();

			if (rt != null)
				rt.draw();
		}
	}

	private Node top;
	private int size = 0;

	// construct an empty set of points
	public KdTree() {

	}

	// is the set empty?
	public boolean isEmpty() {
		return top == null;
	}

	// number of points in the set
	public int size() {
		return size;
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null)
			throw new NullPointerException();

		if (top == null) {
			top = new Node(p);
			top.rect = new RectHV(0, 0, 1, 1);
			size++;
		} else {
			Node node = top.addPoint(p);
			if (node != null)
				size++;
		}
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		if (p == null)
			throw new NullPointerException();

		Point2D nearest = nearest(p);

		return p.equals(nearest);
	}

	// draw all points to standard draw
	public void draw() {
        draw(top);
    }

    private void draw(Node x) {
        if (x == null) {
            return;
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        x.p.draw();
        if (x.vertical()) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.001);
            StdDraw.line(x.p.x(), x.rect.ymin(), x.p.x(), x.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius(0.001);
            StdDraw.line(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.p.y());
        }
        draw(x.lb);
        draw(x.rt);
    }

	// all points that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null)
			throw new NullPointerException();

		Queue<Point2D> result = new Queue<Point2D>();

		addToRange(top, rect, result);

		return result;
	}

	private void addToRange(Node node, RectHV rect, Queue<Point2D> result) {
		if (node == null)
			return;

		if (node.p.x() >= rect.xmin() && node.p.x() <= rect.xmax() && node.p.y() >= rect.ymin()
				&& node.p.y() <= rect.ymax())
			result.enqueue(node.p);

		if (node.vertical()) {
			if (node.p.x() > rect.xmin()) {
				addToRange(node.lb, rect, result);
			}
			if (node.p.x() <= rect.xmax()) {
				addToRange(node.rt, rect, result);
			}
		} else {
			if (node.p.y() > rect.ymin()) {
				addToRange(node.lb, rect, result);
			}
			if (node.p.y() <= rect.ymax()) {
				addToRange(node.rt, rect, result);
			}
		}
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if (p == null)
			throw new NullPointerException();

		double distance = Double.MAX_VALUE;

		Champion result = getChampion(top, p, distance);

		return result.node.p;
	}

	private Champion getChampion(Node node, Point2D p, double distance) {

		if (node == null) {
			return new Champion(null, Double.MAX_VALUE);
		}

		double current = p.distanceSquaredTo(node.p);

		Champion champion;

		if (current < distance) {
			distance = current;
			champion = new Champion(node, distance);
		} else {
			champion = new Champion(null, Double.MAX_VALUE);
		}

		Champion childChampion = null;

		if ((node.vertical() && node.p.x() > p.x()) || (node.horizontal() && node.p.y() > p.y())) {
			childChampion = getChampion(node.lb, p, distance);
			if ((node.rt == null)  || childChampion.distance < node.rt.rect.distanceSquaredTo(p)) {
				return childChampion;
			} else {
				childChampion = getChampion(node.rt, p, distance);
				if (childChampion.distance < distance)
					return childChampion;
			}
		} else {
			childChampion = getChampion(node.rt, p, distance);
			if ((node.lb == null)  || childChampion.distance < node.lb.rect.distanceSquaredTo(p)) {
				return childChampion;
			} else {
				childChampion = getChampion(node.lb, p, distance);
				if (childChampion.distance < distance)
					return childChampion;
			}
		}

		return champion;
	}

	private class Champion {
		Champion(Node node, double distance) {
			this.node = node;
			this.distance = distance;
		}

		Node node;
		double distance;
	}

	// unit testing of the methods (optional)
	public static void main(String[] args) {

	}
}

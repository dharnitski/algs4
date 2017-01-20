import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class KdTree {

	private class Node {

		private Point2D p; // the point
		// private RectHV rect; // the axis-aligned rectangle corresponding to
		// this
		// node
		private Node lb; // the left/bottom subtree
		private Node rt; // the right/top subtree

		Node(Point2D p) {
			this.p = p;
		}

		private boolean type;

		boolean vertical() {
			return type;
		}

		boolean horizontal() {
			return !type;
		}

		Node addPoint(Point2D newP) {
			if ((vertical() && newP.x() < p.x()) || (horizontal() && newP.y() < p.y())) {
				if (lb == null) {
					lb = new Node(newP);
					lb.type = !this.type;
					return lb;
				} else {
					return lb.addPoint(newP);
				}
			} else {
				if (rt == null) {
					rt = new Node(newP);
					rt.type = !this.type;
					return rt;
				} else {
					return rt.addPoint(newP);
				}
			}
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

		if (top == null)
			top = new Node(p);
		else
			top.addPoint(p);
		size++;
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
		if (top != null)
			top.draw();
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
			if (childChampion.distance < distance) {
				return childChampion;
			} else {
				childChampion = getChampion(node.rt, p, distance);
				if (childChampion.distance < distance)
					return childChampion;
			}
		} else {
			childChampion = getChampion(node.rt, p, distance);
			if (childChampion.distance < distance) {
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

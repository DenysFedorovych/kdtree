//package com.company;
import java.util.TreeSet;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
public class PointSET {
    private TreeSet<Point2D> set;
    private int size;
    public PointSET()
    {
        set = new TreeSet<Point2D>();
        this.size=0;
    }
    // construct an empty set of points
    public boolean isEmpty()
    {
        return this.size==0;
    }
    // is the set empty?
    public int size()
    {
     return this.size;
    }
    // number of points in the set
    public void insert(Point2D p)
    {
       if(p==null){throw new IllegalArgumentException("Null argument");}
       if(set.add(p)){size++;}
    }
    // add the point to the set (if it is not already in the set)
    public boolean contains(Point2D p)
    {
        if(p==null){throw new IllegalArgumentException("Null argument");}
        return set.contains(p);
    }
    // does the set contain point p?
    public void draw()
    {
        for(Point2D each : set)
        {each.draw();}
    }
    // draw all points to standard draw
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("Null argument");
        }
        if (!this.isEmpty()) {
            ArrayList<Point2D> points = new ArrayList<>();
            for (Point2D each : set) {
                if (rect.contains(each)) {
                    points.add(each);
                }
            }
            return points;
        }
        else{return null;}
    }
    // all points that are inside the rectangle (or on the boundary)
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Null argument");
        }
        if (!this.isEmpty()) {
            Point2D current;
            if (!p.equals(set.first())) {
                current = set.first();
            } else {
                current = set.last();
            }
            for (Point2D each : set) {
                if (p.distanceTo(each) < p.distanceTo(current)) {
                    current = each;
                }
            }
            return current;
        }
        else{return null;}
    }
    // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {}                 // unit testing of the methods (optional)
}
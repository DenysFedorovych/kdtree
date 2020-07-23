package com.company;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
public class KdTree {
    private int size;
    private Node root;
    private class Node {
        public Node(Node parent, Point2D point){
            this.parent=parent;
            this.point=point;
            this.height=parent.height+1;
            this.lson=null;
            this.rson=null;
        }
        public void LS(Node lson){this.lson=lson;}
        public void RS(Node rson){this.rson=rson;}
        private Node parent;
        private Node lson;
        private Node rson;
        private Point2D point;
        private int height;
    }
    public KdTree()
    {
        this.size=0;
        this.root=null;
    }

    public boolean isEmpty()
    {
        return this.size==0;
    }

    public int size()
    {
        return this.size;
    }

    public void insert(Point2D p)
    {
        if(p==null){throw new IllegalArgumentException("Null argument");}
        if(!this.contains(p))
        {
            Node current=this.root;
            while(current!=null || !current.point.equals(p))
            {
                Point2D curr = current.point;
                if(current.height%2==0)
                {
                    if(curr.y()>p.y()){current=current.lson;
                        if(curr.y()<p.y()){current=current.rson;}
                    }
                }
                if(current.height%2==1)
                {
                    if(curr.x()>p.x()){current=current.lson;
                        if(curr.x()<p.x()){current=current.rson;}
                    }
                }
            }
        }
    }
    // add the point to the set (if it is not already in the set)
    public boolean contains(Point2D p)
    {
        Node current=this.root;
        while(current!=null || !current.point.equals(p))
        {
            Point2D curr = current.point;
            if(current.height%2==0)
            {
                if(curr.y()>p.y()){current=current.lson;
                    if(curr.y()<p.y()){current=current.rson;}
                    else{return true;}
                }
            }
            if(current.height%2==1)
            {
                if(curr.x()>p.x()){current=current.lson;
                    if(curr.x()<p.x()){current=current.rson;}
                    else{return true;}
                }
            }
        }
        return false;
    }
    // does the set contain point p?
    public void draw()
    {
        for(Point2D each : set)
        {each.draw();}
    }
    // draw all points to standard draw
    public Iterable<Point2D> range(RectHV rect)
    {
        ArrayList<Point2D> points = new ArrayList<>();
        for(Point2D each : set)
        {
            if(rect.contains(each)){points.add(each);}
        }
        return points;
    }
    // all points that are inside the rectangle (or on the boundary)
    public Point2D nearest(Point2D p)
    {
        Point2D current;
        if(!p.equals(set.first())){current=set.first();}
        else{current=set.last();}
        for(Point2D each : set)
        {
            if(p.distanceTo(each)<p.distanceTo(current)){current=each;}
        }
        return current;
    }
    // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {}                 // unit testing of the methods (optional)
}
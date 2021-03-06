//package com.company;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

    //             NODE CLASS

    private class Node {
        private Node parent;
        private Node lson;
        private Node rson;
        private Point2D point;
        public int height=0;

        public Node(Node parent, Point2D point) {
            if(parent==null){this.height=1;}
            else{this.height = parent.height + 1;}
            this.parent = parent;
            this.point = point;
            this.lson = null;
            this.rson = null;
        }

        public void LS(Node lson) {
            this.lson = lson;
           // System.out.println("This point  "+this.point.toString()+"  new LSon :  " + lson.point.toString());
        }

        public void RS(Node rson) {
            this.rson = rson;
           // System.out.println("This point  "+this.point.toString()+"  new RSon :  " + rson.point.toString());
        }

        public void draw() {
            if (this.height % 2 == 0) {
                StdDraw.setPenColor(0, 0, 255);
                Point2D a = new Point2D(this.lastrect().xmin(),this.lastrect().ymin());
                a.drawTo(new Point2D(this.lastrect().xmax(),this.lastrect().ymin()));
            }
            if (this.height % 2 == 1) {
                StdDraw.setPenColor(255, 0, 0);
                Point2D a = new Point2D(this.lastrect().xmin(),this.lastrect().ymin());
                a.drawTo(new Point2D(this.lastrect().xmin(),this.lastrect().ymax()));
            }
            StdDraw.setPenColor(0, 0, 0);
            StdDraw.setPenRadius(0.02);
            this.point.draw();
            StdDraw.setPenRadius(0.004);
        }

        public void superdraw() {
            this.draw();
            if (this.lson != null) {
                lson.superdraw();
            }
            if (this.rson != null) {
                rson.superdraw();
            }
        }

        public void search(RectHV rect, ArrayList<Point2D> arr) {
            if (this.height % 2 == 1) {
                if (rect.xmax() < this.point.x() && this.lson!=null) {
                    this.lson.search(rect, arr);
                } else {
                    if (rect.xmin() > this.point.x() && this.rson!=null) {
                        this.rson.search(rect, arr);
                    } else {
                        if (rect.contains(this.point)) {
                            arr.add(this.point);
                        }
                        if(this.lson!=null){this.lson.search(rect, arr);}
                        if(this.rson!=null){this.rson.search(rect, arr);}
                    }
                }
            }
            if (this.height % 2 == 0 && this != null) {
                if (rect.ymax() < this.point.y() && this.lson!=null) {
                    this.lson.search(rect, arr);
                } else {
                    if (rect.ymin() > this.point.y() && this.rson!=null) {
                        this.rson.search(rect, arr);
                    } else {
                        if (rect.contains(this.point)) {
                            arr.add(this.point);
                        }
                        if(this.lson!=null){this.lson.search(rect, arr);}
                        if(this.rson!=null){this.rson.search(rect, arr);}
                    }
                }
            }
        }

        public RectHV lastrect() {
            if (this.parent == null) {
                return new RectHV(this.point.x(), 0, this.point.x(), 1);
            }
//            if (this.height <= 3) {
//                if (this.height % 2 == 0) {
//                    if (this.parent.lson == this) {
//                        return new RectHV(0, this.point.y(), this.parent.point.x(), this.point.y());
//                    }
//                    if (this.parent.rson == this) {
//                        return new RectHV(this.parent.point.x(), this.point.y(), 1,  this.point.y());
//                    }
//                }
//                if (this.height % 2 == 1) {
//                    if (this.parent.lson == this) {
//                        return new RectHV(this.point.x(), 0, this.point.x(), this.parent.point.y());
//                    }
//                    if (this.parent.rson == this) {
//                        return new RectHV(this.point.x(), this.parent.point.y(), this.point.x(), 1);
//                    }
//                }
//            } else {
//                if (this.height % 2 == 0) {
//                    if (this.parent.parent.parent.point.x() < this.parent.point.x()) {
//                        return new RectHV(this.parent.parent.parent.point.x(), this.point.y(), this.parent.point.x(), this.point.y());
//                    } else {
//                        return new RectHV(this.parent.point.x(), this.point.y(), this.parent.parent.parent.point.x(), this.point.y());
//                    }
//                }
//                if (this.height % 2 == 1) {
//                    if (this.parent.parent.parent.point.y() < this.parent.point.y()) {
//                        return new RectHV(this.point.x(), this.parent.parent.parent.point.y(), this.point.x(), this.parent.point.y());
//                    } else {
//                        return new RectHV(this.point.x(), this.parent.point.y(), this.point.x(), this.parent.parent.parent.point.y());
//                    }
//                }
//            }
            if(this.height%2==0)
            {

            }
            return null;
        }
        public void nearest(Point2D a,Point2D champion)
        {
            if(a.distanceTo(this.point)<a.distanceTo(champion)){champion=this.point;}
            if(this.lastrect().distanceTo(a)<a.distanceTo(champion))
            {this.lson.nearest(a,champion);
                this.rson.nearest(a,champion);}
        }
//        public boolean highest()
//        {
//            Node current = this;
//            while(current!=root)
//            {
//                if(current.parent.parent.rson!=this.parent)
//            }
//        }
    }

//             KDTREE CLASS
    private void checknull(Object a)
    {
        if(a==null){throw new IllegalArgumentException("Null argument");}
    }

    private int size;
    private Node root;
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

    public void insert(Point2D p) {
        this.checknull(p);
        if (this.size == 0)
        {
            this.root = new Node(null, p);
            this.size++;
            //System.out.println("This point  "+p.toString()+"  is root");
        }
        else {
            if (!this.contains(p)) {
                Node current = this.root;
                int i=0;
                while (true) {
                    Point2D curr = current.point;
                    if (current.height % 2 == 0) {
                        if (curr.y() >= p.y()) {
                            if(current.lson==null){current.LS(new Node(current,p)); break;}
                            else{current = current.lson;continue;}}
                        if (curr.y() < p.y()) {
                            if(current.rson==null){current.RS(new Node(current,p)); break;}
                            else{current = current.rson;continue;}}

                    }
                    if (current.height % 2 == 1) {
                        if (curr.x() >= p.x()) {
                            if(current.lson==null){current.LS(new Node(current,p)); break;}
                            else{current = current.lson;}}
                        if (curr.x() < p.x()) {
                            if(current.rson==null){current.RS(new Node(current,p)); break;}
                            else{current = current.rson;}}
                        }
                    i++;
                    }
                this.size++;
                }
            }
        }

    // add the point to the set (if it is not already in the set)
    public boolean contains(Point2D p)
    {
        this.checknull(p);
        if(this.isEmpty()){return false;}
        Node current=this.root;
        while(true)
        {
            Point2D curr = current.point;
            if(current.height%2==0)
            {
                if(current.point.equals(p)){return true;}
                if(curr.y()>=p.y()){
                    if(current.lson!=null){current=current.lson; continue;}
                    else{return false;}}
                if(curr.y()<p.y()){
                    if(current.rson!=null){current=current.rson; continue;}
                    else{return false;}}
            }
            if(current.height%2==1)
            {
                if(current.point.equals(p)){return true;}
                if(curr.x()>=p.x()){
                    if(current.lson!=null){current=current.lson;}
                    else{return false;}}
                if(curr.x()<p.x()){
                        if(current.rson!=null){current=current.rson;}
                        else{return false;}}
            }
        }
    }
    // does the set contain point p?
    public void draw()
    {
        this.root.superdraw();
    }
    // draw all points to standard draw
    public Iterable<Point2D> range(RectHV rect)
    {
        this.checknull(rect);
        ArrayList<Point2D> points = new ArrayList<>();
        this.root.search(rect, points);
        if(points.isEmpty()){return null;}
        else{return points;}
    }
    // all points that are inside the rectangle (or on the boundary)
    public Point2D nearest(Point2D p)
    {
        this.checknull(p);
        Point2D nearest = this.root.point;
        this.root.nearest(p,nearest);
        if(this.isEmpty()){return null;}
        else{return nearest;}
    }
    // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {}                 // unit testing of the methods (optional)
}
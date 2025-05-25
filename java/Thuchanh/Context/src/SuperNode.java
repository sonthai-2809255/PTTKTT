import java.util.Objects;

public class SuperNode implements Vertex {
    private Stack<Vertex> fields;

    public SuperNode() {
        this.fields = new Stack();
    }

    @Override
    public String toString() {
        StringBuilder name = new StringBuilder();
        for(Vertex c: fields)
                name.append(c);
        return name.toString();
    }

    @Override
    public Stack getvertex() {
        return fields;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SuperNode superNode = (SuperNode) o;
        return Objects.equals(fields, superNode.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fields);
    }

    public static void main(String[] args) {
        Vertex a = new SuperNode();
        for(int i=0;i<4; i++) a.getvertex().push(i);
        StdOut.println(a);

        Vertex b = new Node(1);
        StdOut.println(b);
    }

    @Override
    public int compareTo(Vertex o) {
        return hashCode()-o.hashCode();
    }
}

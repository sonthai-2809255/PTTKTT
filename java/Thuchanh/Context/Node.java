import java.util.Objects;

public class Node implements Vertex {
    private int v;

    public Node() {
    }
    public Node (int v) {
        this.v = v;
    }
    @Override
    public String toString() {
        return String.format("%d",v);
    }
    @Override
    public Stack getvertex() {
        Stack fields = new Stack();
        fields.push(v);
        return fields;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return v == node.v;
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(v);
    }
    @Override
    public int compareTo(Vertex o) {
        return hashCode()-o.hashCode();
    }
}

// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:12


package rs.ac.bg.etf.pp1.ast;

public class DesigIdent implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String nameDesig;

    public DesigIdent (String nameDesig) {
        this.nameDesig=nameDesig;
    }

    public String getNameDesig() {
        return nameDesig;
    }

    public void setNameDesig(String nameDesig) {
        this.nameDesig=nameDesig;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesigIdent(\n");

        buffer.append(" "+tab+nameDesig);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesigIdent]");
        return buffer.toString();
    }
}

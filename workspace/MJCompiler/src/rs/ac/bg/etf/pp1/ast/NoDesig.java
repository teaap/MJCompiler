// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:12


package rs.ac.bg.etf.pp1.ast;

public class NoDesig extends Designator {

    private DesigIdent DesigIdent;

    public NoDesig (DesigIdent DesigIdent) {
        this.DesigIdent=DesigIdent;
        if(DesigIdent!=null) DesigIdent.setParent(this);
    }

    public DesigIdent getDesigIdent() {
        return DesigIdent;
    }

    public void setDesigIdent(DesigIdent DesigIdent) {
        this.DesigIdent=DesigIdent;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesigIdent!=null) DesigIdent.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesigIdent!=null) DesigIdent.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesigIdent!=null) DesigIdent.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NoDesig(\n");

        if(DesigIdent!=null)
            buffer.append(DesigIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NoDesig]");
        return buffer.toString();
    }
}

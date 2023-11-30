// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:11


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private NumCharBool NumCharBool;
    private NumCharBoolList NumCharBoolList;

    public ConstDecl (Type Type, NumCharBool NumCharBool, NumCharBoolList NumCharBoolList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.NumCharBool=NumCharBool;
        if(NumCharBool!=null) NumCharBool.setParent(this);
        this.NumCharBoolList=NumCharBoolList;
        if(NumCharBoolList!=null) NumCharBoolList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public NumCharBool getNumCharBool() {
        return NumCharBool;
    }

    public void setNumCharBool(NumCharBool NumCharBool) {
        this.NumCharBool=NumCharBool;
    }

    public NumCharBoolList getNumCharBoolList() {
        return NumCharBoolList;
    }

    public void setNumCharBoolList(NumCharBoolList NumCharBoolList) {
        this.NumCharBoolList=NumCharBoolList;
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
        if(Type!=null) Type.accept(visitor);
        if(NumCharBool!=null) NumCharBool.accept(visitor);
        if(NumCharBoolList!=null) NumCharBoolList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(NumCharBool!=null) NumCharBool.traverseTopDown(visitor);
        if(NumCharBoolList!=null) NumCharBoolList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(NumCharBool!=null) NumCharBool.traverseBottomUp(visitor);
        if(NumCharBoolList!=null) NumCharBoolList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NumCharBool!=null)
            buffer.append(NumCharBool.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NumCharBoolList!=null)
            buffer.append(NumCharBoolList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl]");
        return buffer.toString();
    }
}

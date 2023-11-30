// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:11


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclReturn implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private MethodReturnType MethodReturnType;
    private String nameFuncT;

    public MethodDeclReturn (MethodReturnType MethodReturnType, String nameFuncT) {
        this.MethodReturnType=MethodReturnType;
        if(MethodReturnType!=null) MethodReturnType.setParent(this);
        this.nameFuncT=nameFuncT;
    }

    public MethodReturnType getMethodReturnType() {
        return MethodReturnType;
    }

    public void setMethodReturnType(MethodReturnType MethodReturnType) {
        this.MethodReturnType=MethodReturnType;
    }

    public String getNameFuncT() {
        return nameFuncT;
    }

    public void setNameFuncT(String nameFuncT) {
        this.nameFuncT=nameFuncT;
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
        if(MethodReturnType!=null) MethodReturnType.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodReturnType!=null) MethodReturnType.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodReturnType!=null) MethodReturnType.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclReturn(\n");

        if(MethodReturnType!=null)
            buffer.append(MethodReturnType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+nameFuncT);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclReturn]");
        return buffer.toString();
    }
}

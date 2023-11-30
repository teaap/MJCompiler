// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:11


package rs.ac.bg.etf.pp1.ast;

public class FormPars implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private FormParamDeclVar FormParamDeclVar;
    private BracketListForm BracketListForm;

    public FormPars (Type Type, FormParamDeclVar FormParamDeclVar, BracketListForm BracketListForm) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.FormParamDeclVar=FormParamDeclVar;
        if(FormParamDeclVar!=null) FormParamDeclVar.setParent(this);
        this.BracketListForm=BracketListForm;
        if(BracketListForm!=null) BracketListForm.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public FormParamDeclVar getFormParamDeclVar() {
        return FormParamDeclVar;
    }

    public void setFormParamDeclVar(FormParamDeclVar FormParamDeclVar) {
        this.FormParamDeclVar=FormParamDeclVar;
    }

    public BracketListForm getBracketListForm() {
        return BracketListForm;
    }

    public void setBracketListForm(BracketListForm BracketListForm) {
        this.BracketListForm=BracketListForm;
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
        if(FormParamDeclVar!=null) FormParamDeclVar.accept(visitor);
        if(BracketListForm!=null) BracketListForm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(FormParamDeclVar!=null) FormParamDeclVar.traverseTopDown(visitor);
        if(BracketListForm!=null) BracketListForm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(FormParamDeclVar!=null) FormParamDeclVar.traverseBottomUp(visitor);
        if(BracketListForm!=null) BracketListForm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormPars(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParamDeclVar!=null)
            buffer.append(FormParamDeclVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(BracketListForm!=null)
            buffer.append(BracketListForm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormPars]");
        return buffer.toString();
    }
}

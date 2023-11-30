// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:11


package rs.ac.bg.etf.pp1.ast;

public class FormParamDeclVarOne extends FormParamDeclVar {

    private String nameOneBracket;

    public FormParamDeclVarOne (String nameOneBracket) {
        this.nameOneBracket=nameOneBracket;
    }

    public String getNameOneBracket() {
        return nameOneBracket;
    }

    public void setNameOneBracket(String nameOneBracket) {
        this.nameOneBracket=nameOneBracket;
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
        buffer.append("FormParamDeclVarOne(\n");

        buffer.append(" "+tab+nameOneBracket);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParamDeclVarOne]");
        return buffer.toString();
    }
}

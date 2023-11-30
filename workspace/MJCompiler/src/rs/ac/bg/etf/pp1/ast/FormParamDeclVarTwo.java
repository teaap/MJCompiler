// generated with ast extension for cup
// version 0.8
// 3/6/2023 14:4:11


package rs.ac.bg.etf.pp1.ast;

public class FormParamDeclVarTwo extends FormParamDeclVar {

    private String nameTwoBracket;

    public FormParamDeclVarTwo (String nameTwoBracket) {
        this.nameTwoBracket=nameTwoBracket;
    }

    public String getNameTwoBracket() {
        return nameTwoBracket;
    }

    public void setNameTwoBracket(String nameTwoBracket) {
        this.nameTwoBracket=nameTwoBracket;
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
        buffer.append("FormParamDeclVarTwo(\n");

        buffer.append(" "+tab+nameTwoBracket);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParamDeclVarTwo]");
        return buffer.toString();
    }
}

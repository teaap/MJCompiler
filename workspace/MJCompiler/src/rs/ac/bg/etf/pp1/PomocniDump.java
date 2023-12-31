package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;
import rs.etf.pp1.symboltable.concepts.Struct;

public class PomocniDump extends DumpSymbolTableVisitor {

	@Override
	public void visitObjNode(Obj otv) {

		switch (otv.getKind()) {
		case Obj.Con:
			output.append("Con: ");
			break;
		case Obj.Var:
			output.append("Var: ");
			break;
		case Obj.Type:
			output.append("Type: ");
			break;
		case Obj.Meth:
			output.append("Meth: ");
			break;
		case Obj.Fld:
			output.append("Mtx: ");
			break;
		case Obj.Prog:
			output.append("Prog: ");
			break;
		}		
		output.append("(ime)");
		output.append(otv.getName());
		output.append(": ");

		output.append("(tip)");
		if ((Obj.Var == otv.getKind())
				&& "this".equalsIgnoreCase(otv.getName()))
			output.append("");
		else
			otv.getType().accept(this);

		output.append(", ");
		output.append("(adr)");
		output.append(otv.getAdr());
		output.append(", ");
		output.append("(lvl)");
		output.append(otv.getLevel() + " ");		

		if (otv.getType().getKind() == Struct.Enum) {
			output.append("\n");
			nextIndentationLevel();
		}
		
		if (otv.getKind() == Obj.Prog || otv.getKind() == Obj.Meth) {
			output.append("\n");
			nextIndentationLevel();
		}

		for (Obj o : otv.getLocalSymbols()) {
			output.append(currentIndent.toString());
			o.accept(this);
			output.append("\n");
		}

		if (otv.getKind() == Obj.Meth) {
			for (Obj o : otv.getType().getMembers()) {
				output.append(currentIndent.toString());
				o.accept(this);
				output.append("\n");
			}
		}
		
		if (otv.getType().getKind() == Struct.Enum) {
			previousIndentationLevel();
		}

		if (otv.getKind() == Obj.Meth || otv.getKind() == Obj.Prog) {
			previousIndentationLevel();
		}
	}

	@Override
	public void visitStructNode(Struct structToVisit) {
		switch (structToVisit.getKind()) {
		case Struct.Bool:
			output.append("bool");
			break;
		case Struct.Class:
			output.append("void");
			break;
		case Struct.Char:
			output.append("char");
			break;
		case Struct.Int:
			output.append("int");
			break;
		case Struct.Array:
			output.append("Arr of ");

			switch (structToVisit.getElemType().getKind()) {
			case Struct.Int:
				output.append("int");
				break;
			case Struct.None:
				output.append("notype");
				break;
			case Struct.Bool:
				output.append("bool");
				break;
			case Struct.Char:
				output.append("char");
				break;
			case Struct.Array:
				output.append("Arr of ");

				switch (structToVisit.getElemType().getElemType().getKind()) {
				case Struct.Bool:
					output.append("bool");
					break;
				case Struct.None:
					output.append("notype");
					break;
				case Struct.Char:
					output.append("char");
					break;
				case Struct.Int:
					output.append("int");
					break;
				}
				break;
			}
			break;
		}
	}
}
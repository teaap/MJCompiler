package rs.ac.bg.etf.pp1;


import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.Tab;

public class Tip extends Tab {
	
	private static final Struct tbool = new Struct(Struct.Bool);
	private static final Struct tvoid = new Struct(Struct.Class);
	
	public static void init() {
		Tab.init();
		currentScope.addToLocals(new Obj(Obj.Type, "bool", tbool));
	}

	public static Struct boolType() {
		return tbool;
	}
	
	public static Struct voidType() {
		return tvoid;
	}
	

}
package fr.usmb.m1isc.compilation.tp;

import java.util.ArrayList;

public class Tree {

	private NodeType type;
	private Object value;
	// private Tree fg, fd;
	private ArrayList<Tree> subtrees;
	private char format = 0;
	
	public Tree(NodeType type, Object value, Tree f1, Tree f2, Tree f3) {
		this.type = type;
		this.value = value;
		subtrees = new ArrayList<Tree>();
		if (f1 != null)
			subtrees.add(f1);
		if (f2 != null)
			subtrees.add(f2);
		if (f3 != null)
			subtrees.add(f3);
	}

	public Tree(NodeType type, Object value, Tree fg, Tree fd) {
		this.type = type;
		this.value = value;
		subtrees = new ArrayList<Tree>();
		if (fg != null)
			subtrees.add(fg);
		if (fd != null)
			subtrees.add(fd);
	}
	public Tree(NodeType type, Object value, Tree t) {
		this.type = type;
		this.value = value;
		subtrees = new ArrayList<Tree>();
		if (t != null)
			subtrees.add(t);
	}

	public Tree(NodeType type, Object value) {
		this(type, value, null, null);
	}

	public Tree(NodeType type) {
		this(type, null, null, null);
	}

	public NodeType getType() {
		return type;
	}

	public void setType(NodeType type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public char getFormat() {
		return format;
	}

	public void setFormat(char format) {
		this.format = format;
	}

	public String toString() {
		StringBuilder res = new StringBuilder("");
		switch (type) {
		case ENTIER:
			res.append(this.value.toString());
			break;
		case IDENT:
			res.append(this.value);
			break;
		case BOOL:
			res.append(this.value);
			break;
		case IF:
			res.append("IF");
			break;
		case WHILE:
			res.append("WHILE");
			break;
		case LET:
			res.append("LET");
			break;
		case SEMI:
			res.append(";");
			break;
		case PLUS:
			res.append("+");
			break;
		case MOINS:
			res.append("-");
			break;
		case MOINS_UN:
			res.append("-");
			break;
		case MUL:
			res.append("*");
			break;
		case DIV:
			res.append("/");
			break;
		case MOD:
			res.append("MOD");
			break;
		case LT:
			res.append("<");
			break;
		case LTE:
			res.append("<=");
			break;
		case GT:
			res.append(">");
			break;
		case GTE:
			res.append(">=");
			break;
		case AND:
			res.append("AND");
			break;
		case OR:
			res.append("OR");
			break;
		case EGAL:
			res.append("=");
			break;
		case OUTPUT:
			res.append("OUTPUT");
			break;
		case INPUT:
			res.append("INPUT");
			break;
		default:
			res.append("?");
			break;
		}

		if (subtrees.size() > 0) {
			res.append('(');
			for (int i = 0; i < subtrees.size(); ++i) {
				res.append(subtrees.get(i));
				if (i != subtrees.size() - 1) {
					res.append(", ");
				}
			}
			res.append(')');
		}

		return res.toString();
	}
}

package fr.usmb.m1isc.compilation.tp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class CodeGenerator {

	private Tree tree;
	private String filename;
	private HashMap<String, Integer> vars;

	public CodeGenerator(Tree tree, String filename, HashMap<String, Integer> vars) {
		this.tree = tree;
		this.filename = filename;
		this.vars = vars;
	}

	public String getCodeString(Tree t, String regStr) {
		StringBuilder builder = new StringBuilder("");
		NodeType type = t.getType();

		switch (type) {
			case ENTIER:
				builder.append("\tmov " + regStr + ", " + t.getValue().toString() + "\n");
				builder.append("\tpush " + regStr + "\n");
				break;
			case LET:
				// Partie droite
				builder.append(getCodeString(t.getSubtrees().get(1), "eax"));
				// Partie gauche
				builder.append("\tpop eax\n");
				builder.append("\tmov " + t.getSubtrees().get(0).getValue() + ", eax\n");
				builder.append("\tpush eax\n");
				break;
			case IDENT:
				builder.append("\tmov " + regStr + ", " + t.getValue() + "\n");
			case SEMI:
				for (int i = 0; i < t.getSubtrees().size(); ++i) {
					builder.append(getCodeString(t.getSubtrees().get(i), "eax"));
					if (i != t.getSubtrees().size() - 1) {
						builder.append("\tpop eax\n");
					}
				}
				break;
			case PLUS:
				builder.append(getCodeString(t.getSubtrees().get(0), "eax"));
				builder.append(getCodeString(t.getSubtrees().get(1), "ebx"));
				builder.append("\tpop ebx\n");
				builder.append("\tpop eax\n");
				builder.append("\tadd eax, ebx\n");
				builder.append("\tpush eax\n");
				break;
			case MUL:
				builder.append(getCodeString(t.getSubtrees().get(0), "eax"));
				builder.append(getCodeString(t.getSubtrees().get(1), "ebx"));
				builder.append("\tpop ebx\n");
				builder.append("\tpop eax\n");
				builder.append("\tmul eax, ebx\n");
				builder.append("\tpush eax\n");
				break;
			case DIV:
				builder.append(getCodeString(t.getSubtrees().get(0), "eax"));
				builder.append(getCodeString(t.getSubtrees().get(1), "ebx"));
				builder.append("\tpop ebx\n");
				builder.append("\tpop eax\n");
				builder.append("\tdiv eax, ebx\n");
				builder.append("\tpush eax\n");
				break;
		}
		
		return builder.toString();
	}

	public void generate() {
		try {
			File file = new File(filename);
			file.delete();
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringBuilder dataSegment = new StringBuilder("");
		dataSegment.append("DATA SEGMENT\n");
		for (String var : vars.keySet()) {
			dataSegment.append("\t" + var + " DD\n");
		}
		dataSegment.append("DATA ENDS\n");

		StringBuilder codeSegment = new StringBuilder("");
		codeSegment.append("CODE SEGMENT\n");

		StringBuilder codeSegmentBody = new StringBuilder("");
		codeSegmentBody.append(getCodeString(tree, "eax"));

		codeSegment.append(codeSegmentBody);
		codeSegment.append("CODE ENDS\n");

		try {
			FileWriter writer = new FileWriter(filename);
			writer.write(dataSegment.toString());
			writer.write(codeSegment.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}

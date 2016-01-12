package net.torbenvoltmer.fhdw.calculator.scanner.states;

import net.torbenvoltmer.fhdw.calculator.scanner.Scanner;
import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.CommentNotClosedException;
import net.torbenvoltmer.fhdw.calculator.scanner.exceptions.NoNextCharacterException;
import net.torbenvoltmer.fhdw.calculator.symbols.Comment;

public class CommentState extends State{

	private String commentText;
	
	public CommentState(Scanner scanner) {
		super(scanner);
		commentText = "";
		scanner.skip();
		scanner.skip();
	}
	
	
	@Override
	public void scan(Character c) throws CommentNotClosedException {
		Scanner scanner = this.getScanner();
		
		try {
			if(!c.equals('*') && !scanner.getNextChar().equals('/')){
				commentText += c;
				scanner.skip();			
			}else{
				scanner.skip();
				scanner.skip();
				scanner.addSymbol(new Comment(commentText));
				scanner.setState(new SelectionState(scanner));			
			}
			
			
		} catch (NoNextCharacterException e) {
			throw new CommentNotClosedException(e);
		}
		
	}

	@Override
	public void finish() {
		this.getScanner().addSymbol(new Comment(commentText));
	}

	

}

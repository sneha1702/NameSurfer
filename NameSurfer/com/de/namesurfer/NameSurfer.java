package com.de.namesurfer;
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.*;

import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	/* Private instance variables */
	private JLabel label;
	private TextField name;
	private NameSurferGraph canvas;
	private NameSurferDataBase namesdb;
	private Button graph;
    private Button clear;
	
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		
		label = new JLabel("Name");
		name = new TextField(20);

		add(label, SOUTH);
		add(name,SOUTH);

		graph = new Button("Graph");
		add(graph, SOUTH);
		
		clear = new Button("Clear");
		add(clear, SOUTH);
				
		canvas = new NameSurferGraph();
		add(canvas);
		
		addActionListeners();
		name.addActionListener(this);
		
		 //reads the file of names and adds to the NameSurferDataBase
        namesdb = new NameSurferDataBase(NAMES_DATA_FILE);
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Clear")) {
			canvas.clear();
			canvas.update();
		}
		else {
			String enteredName = name.getText();
			NameSurferEntry rankings = namesdb.findEntry(enteredName);
			if(rankings != null) {
				canvas.addEntry(rankings);
				canvas.update();
			}
		}
	}
}

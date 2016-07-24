package kariminf.langpi.wordnet.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.UIManager.LookAndFeelInfo;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;

public class WNsearch extends JFrame {


	JTextField input = new JTextField();
	JTextArea output = new JTextArea();
	IDictionary dict = new Dictionary(new File("wordnetDB/dict/")) ;
	

	public WNsearch() throws IOException {
		super("Search wordnet 3.0");
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		setSize(700, 500);
        setMinimumSize(new Dimension(700, 500));
        
        input.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		
		//getContentPane().setLayout(new FlowLayout());
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        JButton findBtn = new JButton("Find");
		MouseListener mouseListener = new MouseAdapter() {
 			public void mouseClicked(MouseEvent e) {
 				find();
 				
 			}
 		};
 		
 		ActionListener actionListener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				find();
			}
 		};
 		input.addActionListener(actionListener);
 		findBtn.addMouseListener(mouseListener);
 		output.setLineWrap(true);
 		
 		
 		add(input);
 		add(findBtn);
 		JScrollPane outputscrl = new JScrollPane(output);
 		outputscrl.setPreferredSize(new Dimension(700, 50));
 		add(outputscrl);
 		
 		dict.open();
 		setVisible(true);
	}
	
	private void find(){
		
		String word = input.getText().trim();
		String result = "Nouns: \n";
		result += "=====================\n\n";
		result += getMeanings(word, POS.NOUN);
		
		result += "\n\nVerbs: \n";
		result += "=====================\n\n";
		result += getMeanings(word, POS.VERB);
		
		result += "\n\nAdjectives: \n";
		result += "=====================\n\n";
		result += getMeanings(word, POS.ADJECTIVE);
		
		result += "\n\nAdverbs: \n";
		result += "=====================\n\n";
		result += getMeanings(word, POS.ADVERB);
		
		output.setText(result);
		
	}
	
	
	private String getMeanings(String word, POS pos){
		String result = "";
		IIndexWord idxWord = dict.getIndexWord (word , pos) ;
		if(idxWord == null) return "";
		
		int i= 1;
		
		for (IWordID iwordid: idxWord.getWordIDs()){
			result += i + " - ";
			result += "(" + iwordid.getSynsetID().getOffset() + ") ";
			IWord iword = dict.getWord(iwordid);
			result += ": ";
			for(IWord iword2 : iword.getSynset().getWords())
				result += iword2.getLemma() + ", ";
			result += "\n==> " + iword.getSynset().getGloss() + "\n\n";
			i++;
		}
		return result;
	}

	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args)  {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					System.out.println(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
	
		try {
			new WNsearch();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

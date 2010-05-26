package at.tugraz.iicm.matrixexplorer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;


public class MatrixDisplay extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    public MatrixDisplay() {
        super(new GridLayout(1,0));

        DataSetParser parser = new DataSetParser();
        
        String[] columnNames = parser.getNames();
        String[][] data = parser.getMatrix();

        final JTable table = new JTable(data, columnNames);
        
        table.setAutoCreateRowSorter(true);
//        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);




        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        table.setDragEnabled(true);
        table.setTransferHandler(new TableTransferHandler());
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createTitledBorder("Table"));
        //Add the scroll pane to this panel.
        add(scrollPane);
    }



    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MatrixExplorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        MatrixDisplay newContentPane = new MatrixDisplay();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}



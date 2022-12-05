import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class MyWindow implements ActionListener, ChangeListener {
    private boolean[] boxesChecked;
    private String[] input = new String[10];
    //private JTextField[] textFields = new JTextField[10];
    private ArrayList<JTextField> textFields = new ArrayList<>();
    private ArrayList<JCheckBox> boxes = new ArrayList<>();

    private JTextField tf1;
    private JTextField tf5;
    private JTextField tf2;
    private JTextField tf4;
    private JTextField tf3;
    private JCheckBox box1;
    private JCheckBox box2;
    private JCheckBox box3;
    private JCheckBox box4;
    private JCheckBox box5;
    private JTextField tf6;
    private JCheckBox box6;
    private JTextField tf8;
    private JTextField tf7;
    private JTextField tf9;
    private JTextField tf10;
    private JCheckBox box7;
    private JCheckBox box8;
    private JCheckBox box9;
    private JCheckBox box10;
    private JPanel chartPanel;
    private JPanel panel1;


    public MyWindow() {
        boxesChecked = new boolean[10];
        Arrays.fill(boxesChecked, false);
        Collections.addAll(boxes,box1,box2,box3,box4,box5,box6,box7,box8,box9,box10);

        box1.addChangeListener(this);
        box2.addChangeListener(this);
        box3.addChangeListener( this);
        box4.addChangeListener(this);
        box5.addChangeListener(this);
        box6.addChangeListener(this);
        box7.addChangeListener(this);
        box8.addChangeListener(this);
        box9.addChangeListener(this);
        box10.addChangeListener(this);

        tf1.addActionListener(this);
        tf2.addActionListener(this);
        tf3.addActionListener(this);
        tf4.addActionListener(this);
        tf5.addActionListener(this);
        tf6.addActionListener(this);
        tf7.addActionListener(this);
        tf8.addActionListener(this);
        tf9.addActionListener(this);
        tf10.addActionListener(this);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MyWindow");
        MyWindow window = new MyWindow();
        frame.add(window.panel1, BorderLayout.NORTH);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(0, "", ""); //creating empty chart

        JFreeChart chart = ChartFactory.createBarChart("Chart", "X", "y", dataset,
                PlotOrientation.VERTICAL, false, true, false);

        window.chartPanel.setLayout(new BorderLayout());
        window.chartPanel.add(new ChartPanel(chart));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void createChart() { //Zmiana szerokości pasków/odstępów między nimi
        chartPanel.removeAll();
        chartPanel.revalidate();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < 10; i++) {
            if (boxesChecked[i]) {
                try {
                    System.out.println("TEXT field" + i + ":" + input[i]);
                    dataset.setValue(Integer.parseInt(input[i]),String.valueOf(i), input[i]);
                } catch (NumberFormatException e) {
                    System.out.println("Wrong format" + input[i]);
                }
            }
        }
        JFreeChart chart = ChartFactory.createBarChart("Chart", "X", "y", dataset);


        //CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        ((BarRenderer) categoryPlot.getRenderer()).setBarPainter(new StandardBarPainter());
        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();

//        final CategoryItemRenderer renderer = new CustomRenderer(
//                new Paint[] {Color.red, Color.blue, Color.green,
//                        Color.yellow, Color.orange, Color.cyan,
//                        Color.magenta, Color.blue, Color.darkGray, Color.PINK}
//        );

        Paint []barColors = new Paint[10];
        barColors[0] = new Color(17, 65, 74);
        barColors[1] = new Color(150, 100, 33);
        barColors[2] = new Color(17, 74, 60);
        barColors[3]= new Color(33, 150, 145);
        barColors[4] = new Color(17, 74, 38);
        barColors[5] = new Color(74, 17, 17);
        barColors[6] = new Color(33, 150, 37);
        barColors[7] = new Color(31, 17, 74);
        barColors[8] = new Color(74, 17, 59);
        barColors[9] = new Color(150, 33, 62);




        //ZMIANA WYGLADU
        //renderer.setSeriesPaint(0, new Color(28,120,140));
        chart.setBackgroundPaint(new Color(103,194,190));
        chart.getPlot().setBackgroundPaint(new Color(103,194,190));
        renderer.setItemMargin(-0.75);
        renderer.setMaximumBarWidth(0.5);
        //renderer.setDefaultOutlineStroke();

//        final ItemLabelPosition p = new ItemLabelPosition(
//                ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 45.0
//        );
//        renderer.setDefaultPositiveItemLabelPosition(p);
//        categoryPlot.setRenderer(renderer);

        int barNumber=0;
        int counter=0;
        for(boolean b:boxesChecked){
            try{
                if(b){
                    renderer.setSeriesPaint(barNumber,barColors[counter]);
                    barNumber++;
                }
            }
            catch (Exception e){
                System.out.println("Podano zle dane");
            }
            counter++;
        }

        categoryPlot.setRenderer(renderer);
        chartPanel.add(new ChartPanel(chart));
        chartPanel.repaint();
    }

 /*   class CustomRenderer extends BarRenderer{
        private Paint[] colors;
        public CustomRenderer (final Paint[] colors){
            this.colors=colors;
        }
        public Paint getItemPaint(final int row, final int column){
            return this.colors[column % this.colors.length];
        }
    }*/

    public void generateTextFields(){
        Collections.addAll(textFields,tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9,tf10);
    }

    public void generateBoxes(){
        Collections.addAll(boxes,box1,box2,box3,box4,box5,box6,box7,box8,box9,box10);
    }

    public void setTextFields() {
        generateTextFields();
        for(int i =0; i<10; i++){
            input[i] = textFields.get(i).getText();
        }
    }

    //Wywołanie akcji poprzez zmianę stanu
    @Override
    public void actionPerformed(ActionEvent e) {

        int id = 0;
        for (int i=0; i<10; i++){                       //znajduję id pola wywołującego akcję
            if(e.getSource() == textFields.get(i)){
                id=i;
            }
        }

        if(!Objects.equals(input[id], textFields.get(id).getText())){
            input[id] = textFields.get(id).getText();
            createChart();
        }
    }

    //Wywołanie akcji poprzez zmiane stanu pola wyboru
    @Override
    public void stateChanged(ChangeEvent e) {

        int id = 0;
        for (int i=0; i<10; i++){                       //znajduję id pola wywołującego akcję
            if(e.getSource() == boxes.get(i)){
                id=i;
            }
        }

        if(boxesChecked[id] != boxes.get(id).isSelected()){
            boxesChecked[id] = boxes.get(id).isSelected();
            setTextFields();
            createChart();
        }
    }
}



package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.border.Border;

public class backUp extends JDialog{
    private static final Insets EMPTY__INSETS = new Insets(0, 0, 0, 0);
    private static final String ADD_BUTTON_LABEL = "Add>>";
    private static final String REMOVE_BUTTON_LABEL = "<<Remove";
    private static final String DEFAULT_CHOICE = "Available Databases";
    private static final String YOUR_CHOICE = "Selected Databases";
    private JLabel sourceLabel, destLabel;
    private JList sourceList, destList;
    private SortedListModel sourceListModel, destListModel;
    private JButton addButton, removeButton;
    
    public backUp(JFrame parent) throws IOException{
        super(parent, "Back-Up Database", false);
        
        Image img = ImageIO.read(this.getClass().getResource("/images/backup_1.png"));
        
        sourceLabel=new JLabel("Source List");
        destLabel=new JLabel("Destination List");
        sourceList=new JList();
        destList=new JList();
        addButton=new JButton("add");
        removeButton=new JButton("Remove");
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ae){
                dispose();
            }
        });
        
        setSize(600, 400);
        setIconImage(img);
        setLocationRelativeTo(null);
        layoutControls();
    }
    private void layoutControls(){
        JPanel backPanel=new JPanel();
        backPanel.setBackground(Color.white);
        JPanel buttonPanel=new JPanel();
        backPanel.setBackground(Color.white);
        
        int space=15;
        Border spaceBorder=BorderFactory.createEmptyBorder(space,space, space, space);
        Border titlebackPanel=BorderFactory.createTitledBorder("Choose Backup Options");
        
        backPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titlebackPanel));
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        
        //BackPanel
        backPanel.setLayout(new GridBagLayout());
        
        addGridItems(backPanel, sourceLabel, 0, 0, 1, 1, GridBagConstraints.WEST);              addGridItems(backPanel, destLabel, 2, 0, 1, 1, GridBagConstraints.EAST);
        addGridItems(backPanel, sourceList, 1, 0, 2, 1, GridBagConstraints.EAST);               addGridItems(backPanel, destList, 4, 0, 2, 1, GridBagConstraints.WEST);
        
        
        //ButtonPanel
        addGridItems(buttonPanel, addButton, 0, 1, 1, 1, GridBagConstraints.WEST);
        addGridItems(buttonPanel, removeButton, 0, 1, 1, 1, GridBagConstraints.EAST);
        
        
        setLayout(new BorderLayout());
        add(backPanel, BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);
        
        
    }
      private void addGridItems(JPanel panel, JComponent cmp, int x, int y, int width, int height, int align){
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        
        gc.gridwidth = width;
        gc.gridheight = height;
        
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        
        gc.insets = new Insets(5,5,5,5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        
        panel.add(cmp, gc);
    }
     
    public String getSourceChoicesTitle(){
        return sourceLabel.getText();
    }
    public void setSourceChoicesTitle(String newValue){
        sourceLabel.setText(newValue);
    }
    public String getDestinationChoicesTitle(){
        return destLabel.getText();
    }
    public void setDestinationChoicesTitle(String newValue){
        destLabel.setText(newValue);
    }
    public void clearSourceListModel(){
        sourceListModel.clear();
    }
    public void clearDestinationListModel(){
        destListModel.clear();
    }
    public void addSourceElements(ListModel newValue){
        fillListModel(sourceListModel, newValue);
    }
    public void setSourceElements(ListModel newValue){
        clearSourceListModel();
        addSourceElements(newValue);
    }
    public void addDestinationElements(ListModel newValue){
        fillListModel(destListModel, newValue);
    }
    private void fillListModel(SortedListModel model, ListModel newValues){
        int size = newValues.getSize();
        for(int i = 0; i < size; i++){
            model.add(newValues.getElementAt(i));
        }
    }
    public void addSourceElements(Object newValue[]) {
      fillListModel(sourceListModel, newValue);
    }

    public void setSourceElements(Object newValue[]) {
      clearSourceListModel();
      addSourceElements(newValue);
    }

    public void addDestinationElements(Object newValue[]) {
      fillListModel(destListModel, newValue);
    }

    private void fillListModel(SortedListModel model, Object newValues[]) {
      model.addAll(newValues);
    }

    public Iterator sourceIterator() {
      return sourceListModel.iterator();
    }

    public Iterator destinationIterator() {
      return destListModel.iterator();
    }
    public void setSourceCellRenderer(ListCellRenderer newValue) {
      sourceList.setCellRenderer(newValue);
    }

    public ListCellRenderer getSourceCellRenderer() {
      return sourceList.getCellRenderer();
    }

    public void setDestinationCellRenderer(ListCellRenderer newValue) {
      destList.setCellRenderer(newValue);
    }

    public ListCellRenderer getDestinationCellRenderer() {
      return destList.getCellRenderer();
    }

    public void setVisibleRowCount(int newValue) {
      sourceList.setVisibleRowCount(newValue);
      destList.setVisibleRowCount(newValue);
    }

    public int getVisibleRowCount() {
      return sourceList.getVisibleRowCount();
    }

    public void setSelectionBackground(Color newValue) {
      sourceList.setSelectionBackground(newValue);
      destList.setSelectionBackground(newValue);
    }

    public Color getSelectionBackground() {
      return sourceList.getSelectionBackground();
    }

    public void setSelectionForeground(Color newValue) {
      sourceList.setSelectionForeground(newValue);
      destList.setSelectionForeground(newValue);
    }

    public Color getSelectionForeground() {
      return sourceList.getSelectionForeground();
    }

    private void clearSourceSelected() {
      Object selected[] = sourceList.getSelectedValues();
      for (int i = selected.length - 1; i >= 0; --i) {
        sourceListModel.removeElement(selected[i]);
      }
      sourceList.getSelectionModel().clearSelection();
    }

    private void clearDestinationSelected() {
      Object selected[] = destList.getSelectedValues();
      for (int i = selected.length - 1; i >= 0; --i) {
        destListModel.removeElement(selected[i]);
      }
      destList.getSelectionModel().clearSelection();
    }

}
class SortedListModel extends AbstractListModel{
    
    SortedSet model;
    
    //constructor for the sorted list model
    public SortedListModel(){
        model = new TreeSet();
    }

    @Override
    public int getSize() {
        return model.size();
    }

    @Override
    public Object getElementAt(int i) {
        return model.toArray()[i];
    }
    
    public void add(Object element){
        if(model.add(element)){
            fireContentsChanged(this, 0, getSize());
        }
    }
    public void addAll(Object elements[]){
        Collection c = Arrays.asList(elements);
        model.add(c);
    }
    public void clear(){
        model.clear();
        fireContentsChanged(this, 0, getSize());
    }
    public boolean contains(Object element){
        return model.contains(element);
    }
    public Object firstElement(){
        return model.first();
    }
    public Iterator iterator(){
        return model.iterator();
    }
    public Object lastElement(){
        return model.last();
    }
    public boolean removeElement(Object element){
        boolean removed = model.remove(element);
        if(removed){
            fireContentsChanged(this, 0, getSize());
        }
        return removed;
    }
}

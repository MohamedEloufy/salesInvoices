package Ui;

import javax.imageio.IIOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import java.util.ArrayList;


public class FileOperations {

    Ui ui;

    protected void saveContent(JTable table,String FileName) throws IOException {
        ui=new Ui();
        JFileChooser fc =new JFileChooser();

        fc.setAcceptAllFileFilterUsed(false);
        fc.setSelectedFile(new File(FileName));
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Excel Documents","csv"));
        int result= fc.showSaveDialog(ui);
        if (result==JFileChooser.APPROVE_OPTION)
        {
            String path = fc.getSelectedFile().getPath();
            FileOutputStream fos=null;
            try {

                fos=new FileOutputStream(path);
                for(int i =0;i<table.getRowCount();i++)
                {
                    for (int j = 0; j < table.getColumnCount();j++)
                    {
                        byte[] b = (table.getValueAt(i,j).toString()+",").getBytes();
                        fos.write(b);
                    }
                    fos.write("\n".getBytes());
                }
            }catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }catch (IIOException e)
            {
                e.printStackTrace();
            }finally {
                fos.close();
            }
        }
    }




}

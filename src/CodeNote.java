/**
 * Created by 孙平 on 2015/9/23.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.event.*;
import java.util.*;

public class CodeNote extends JFrame{
    DB db=new DB();
    Vector<String> s=new Vector<String>(Arrays.asList(db.getList()));

    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();//面板,装按钮
    JTextArea textEditor =new JTextArea();;//文本域
    JList listCode = new JList(s);//列表
    JScrollPane JSP= new JScrollPane(listCode);

    JButton btnNew;
    JButton btnSave;
    JButton btnDelete;
    JButton btnCheat;
    CodeNote(){
        super("CodeNote");
        Container con=getContentPane();

        setBounds(225, 60, 900, 600);//设置窗体初始化位置及大小
        con.setLayout(null);//设绝对布局
        con.setBackground(Color.gray);//背景颜色

        //List事件
        listCode.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                textEditor.setText(db.getCode(listCode.getSelectedValue().toString()));
            }

        });

        FlowLayout flow=new FlowLayout();

        btnNew=new JButton("新建");
        btnSave=new JButton("保存");
        btnDelete=new JButton("删除");
        btnCheat=new JButton("查重");

        btnNew.setFocusPainted(false);
        btnSave.setFocusPainted(false);
        btnDelete.setFocusPainted(false);
        btnCheat.setFocusPainted(false);

        btnNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String str = JOptionPane.showInputDialog(null, "Enter some text : ", "title", 1);
                if (db.add(str)) {
                    s.add(str);
                    listCode.updateUI();
                } else {
                    JOptionPane.showMessageDialog(null, "添加代码失败");
                }
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String item = listCode.getSelectedValue().toString();
                    db.delete(item);
                    s.remove(item);
                    listCode.updateUI();
                } catch (Exception ee) {
                    //ee.printStackTrace();
                }
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String item = listCode.getSelectedValue().toString();
                    String newcode=textEditor.getText();
                    db.edit(item, newcode);
                } catch (Exception ee) {
                    //ee.printStackTrace();
                }
            }
        });
        btnCheat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[][] code=new String[s.size()][];
                for(int i=0;i<s.size();i++)code[i]=Similary.prepare(db.getCode(s.get(i)));
                String simstr="";
                for(int i=0;i<s.size();i++)for(int j=i+1;j<s.size();j++)
                {
                    double sim=Similary.similary(code[i],code[j]);
                    if(sim>0.7)simstr+="similary of code "+i+" and code "+ j+" is:"+sim+"\n";
                }
                if(!simstr.isEmpty())JOptionPane.showMessageDialog(null, simstr);
                else JOptionPane.showMessageDialog(null, "no similary>0.7");
            }
        });
        p1.setLayout(flow);
        p2.setLayout(null);
        p1.setBounds(0, 1, 100, 139);
        p2.setBounds(0, 141, 100, 439);
        JSP.setBounds(0,0,100,430);
        listCode.setSize(100, 400);
        textEditor.setBounds(101,1,799,570);


        btnNew.setPreferredSize(new Dimension(100, 30));
        btnSave.setPreferredSize(new Dimension(100, 30));
        btnDelete.setPreferredSize(new Dimension(100, 30));
        btnCheat.setPreferredSize(new Dimension(100, 30));



        p1.add(btnNew);
        p1.add(btnSave);
        p1.add(btnDelete);
        p1.add(btnCheat);
        p2.add(JSP);
        con.add(p1);
        con.add(p2);
        con.add(textEditor);




        setVisible(true);//设置窗体为可见
        this.setResizable(false);//设置窗体取消最大化及拉伸（固定原先窗体大小）
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭JAVA虚拟机



    }

    public static void main(String[] args) {
        CodeNote one = new CodeNote();
    }

}

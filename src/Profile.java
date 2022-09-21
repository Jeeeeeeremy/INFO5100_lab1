import model.person;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
/*
 * Created by JFormDesigner on Sun Sep 18 00:57:56 EDT 2022
 */



/**
 * @author yibin
 */
public class Profile extends JFrame {
    //static String  regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    //used to check email address format
    static String  regEx1 = "^(.+)@(\\\\S+)$";
    Pattern emailcheck = Pattern.compile(regEx1);
    person p = new person();
    //used to keep photo
    ImageIcon photo;

    public Profile() {
        initComponents();
        //make degree2 option invisible
        degree2(false);
    }

    //submit action
    private void submit(ActionEvent e) {
        // TODO add your code here
        if (submitcheck()){
            //assign values to model
            p.setFirstName(firstName.getText());
            p.setLastName(lastName.getText());
            p.setDOB(DOBmonth.getText()+"/"+DOBday.getText()+"/"+DOByear.getText());
            p.setAge(age.getText());
            p.setNumber(number.getText());
            p.setEmail(email.getText());
            p.setStreetLine1(addline1.getText());
            p.setStreetLine2(addline2.getText());
            p.setCity(city.getText());
            p.setCountry(country.getText());
            p.setAffiliation(affiliation.getText());
            p.setMajor(major.getText());
            p.setCareer(career.getText());
            if (degree1.getText().length()!=0){
                p.setDegree1(degree1.getText());
                p.setDegree1Startdate(d1startmonth.getText()+"/"+d1startyear.getText());
                p.setDegree1End(d1endmonth.getText()+"/"+d1endyear.getText());
                //add degree2 information when degree1 is completed
                if (degree2.getText().length()!=0){
                    p.setDegree2(degree2.getText());
                    p.setDegree2Startdate(d2startmonth.getText()+"/"+d2startyear.getText());
                    p.setDegree2End(d2endmonth.getText()+"/"+d2endyear.getText());
                }
            }else {
                JOptionPane.showMessageDialog(this,"the data of degree(s) will be ignored if you leave the corresponding degree title blank");
            }
            p.setPhoto(photo);
            clear();
        }

    }
    //clear all fields after submitting
    private void clear(){
        firstName.setText("");
        lastName.setText("");
        DOBmonth.setText("");
        DOBday.setText("");
        DOByear.setText("");
        age.setText("");
        number.setText("");
        email.setText("");
        addline1.setText("");
        addline2.setText("");
        city.setText("");
        country.setText("");
        affiliation.setText("");
        major.setText("");
        career.setText("");
        degree1.setText("");
        d1startmonth.setText("");
        d1startyear.setText("");
        d1endmonth.setText("");
        d1endyear.setText("");
        degree2.setText("");
        d2startmonth.setText("");
        d2startyear.setText("");
        d2endmonth.setText("");
        d2endyear.setText("");
        button1.setVisible(true);
        photo=null;
        JOptionPane.showMessageDialog(this,"successfully uploaded");
    }
    //only show degree2 option when the button is clicked
    private void degree2(boolean flag){
        degree2.setVisible(flag);
        d2startmonth.setVisible(flag);
        d2startyear.setVisible(flag);
        d2endmonth.setVisible(flag);
        d2endyear.setVisible(flag);
        label26.setVisible(flag);
        label30.setVisible(flag);
    }



    private boolean degree1check(){
        Pattern digitp = Pattern.compile("^[-\\+]?[\\d]*$");
        if (degree1.getText().length()!=0){
            if (d1startmonth.getText().length()==0||!digitp.matcher(d1startmonth.getText()).matches()||Integer.valueOf(d1startmonth.getText())>12||Integer.valueOf(d1startmonth.getText())<1){
                JOptionPane.showMessageDialog(this,"degree1 start date month is missing");
                return false;
            }
            if (d1startyear.getText().length()==0||!digitp.matcher(d1startyear.getText()).matches()||Integer.valueOf(d1startyear.getText())<1970){
                JOptionPane.showMessageDialog(this,"degree1 start date year is missing");
                return false;
            }
            if (d1endmonth.getText().length()==0||!digitp.matcher(d1endmonth.getText()).matches()||Integer.valueOf(d1endmonth.getText())>12||Integer.valueOf(d1endmonth.getText())<1){
                JOptionPane.showMessageDialog(this,"degree1 end date month is missing");
                return false;
            }
            if (d1endyear.getText().length()==0||!digitp.matcher(d1endyear.getText()).matches()||Integer.valueOf(d1endyear.getText())<1970){
                JOptionPane.showMessageDialog(this,"degree1 end date year is missing");
                return false;
            }
            if (Integer.valueOf(d1startyear.getText())>Integer.valueOf(d1endyear.getText())){
                JOptionPane.showMessageDialog(this,"end date should later than start date");
                return false;
            }else if (Integer.valueOf(d1startyear.getText())==Integer.valueOf(d1endyear.getText())&&Integer.valueOf(d1startmonth.getText())>Integer.valueOf(d1endmonth.getText())){
                JOptionPane.showMessageDialog(this,"end date should later than start date");
                return false;
            }
        }
        return true;
    }
    //check the data integrity
    private boolean submitcheck(){
        Pattern digitp = Pattern.compile("^[-\\+]?[\\d]*$");
        if (lastName.getText().length()==0){
            JOptionPane.showMessageDialog(this,"lastName is missing");
            return false;
        }
        if (firstName.getText().length()==0){
            JOptionPane.showMessageDialog(this,"firstName is missing");
            return false;
        }
        if (DOBmonth.getText().length()==0||!digitp.matcher(DOBmonth.getText()).matches()||Integer.valueOf(DOBmonth.getText())>12||Integer.valueOf(DOBmonth.getText())<1){
            JOptionPane.showMessageDialog(this,"DOB month is wrong");
            return false;
        }
        if (DOBday.getText().length()==0||!digitp.matcher(DOBday.getText()).matches()||Integer.valueOf(DOBday.getText())>31||Integer.valueOf(DOBday.getText())<1){
            JOptionPane.showMessageDialog(this,"DOB day is wrong");
            return false;
        }
        if (DOByear.getText().length()==0||!digitp.matcher(DOByear.getText()).matches()||Integer.valueOf(DOByear.getText())>2022||Integer.valueOf(DOByear.getText())<1970){
            JOptionPane.showMessageDialog(this,"DOB year is wrong");
            return false;
        }
        if (age.getText().length()==0||!digitp.matcher(age.getText()).matches()||Integer.parseInt(age.getText())<1){
            JOptionPane.showMessageDialog(this,"age is wrong");
            return false;
        }
        if (number.getText().length()==0||!digitp.matcher(number.getText()).matches()){
            JOptionPane.showMessageDialog(this,"telephone number is missing");
            return false;
        }
        Matcher tmpmatch1 = emailcheck.matcher(email.getText());
        if (email.getText().length()==0||tmpmatch1.matches()){
            JOptionPane.showMessageDialog(this,"email is wrong");
            return false;
        }
        if (addline1.getText().length()==0){
            JOptionPane.showMessageDialog(this,"street line1 is missing");
            return false;
        }
        if (city.getText().length()==0){
            JOptionPane.showMessageDialog(this,"city is missing");
            return false;
        }
        if (country.getText().length()==0){
            JOptionPane.showMessageDialog(this,"country is missing");
            return false;
        }
        if (!degree1check())
            return false;

        if (degree2.getText().length()!=0){
            if (d2startmonth.getText().length()==0||!digitp.matcher(d2startmonth.getText()).matches()||Integer.valueOf(d2startmonth.getText())>12||Integer.valueOf(d2startmonth.getText())<1){
                JOptionPane.showMessageDialog(this,"degree2 start date month is missing");
                return false;
            }
            if (d2startyear.getText().length()==0||!digitp.matcher(d2startyear.getText()).matches()||Integer.valueOf(d2startyear.getText())<1970){
                JOptionPane.showMessageDialog(this,"degree2 start date year is missing");
                return false;
            }

            if (d2endmonth.getText().length()==0||!digitp.matcher(d2endmonth.getText()).matches()||Integer.valueOf(d2endmonth.getText())>12||Integer.valueOf(d2endmonth.getText())<1){
                JOptionPane.showMessageDialog(this,"degree2 end date month is missing");
                return false;
            }
            if (d2endyear.getText().length()==0||!digitp.matcher(d2endyear.getText()).matches()||Integer.valueOf(d2endyear.getText())<1970){
                JOptionPane.showMessageDialog(this,"degree2 end date year is missing");
                return false;
            }
            if (Integer.valueOf(d2startyear.getText())>Integer.valueOf(d2endyear.getText())){
                JOptionPane.showMessageDialog(this,"end date should later than start date");
                return false;
            }else if (Integer.valueOf(d2startyear.getText())==Integer.valueOf(d2endyear.getText())&&Integer.valueOf(d2startmonth.getText())>Integer.valueOf(d2endmonth.getText())){
                JOptionPane.showMessageDialog(this,"end date should later than start date");
                return false;
            }
        }

        //leave for photo check
        if (photo==null){
            JOptionPane.showMessageDialog(this,"one photo is required");
            return false;
        }
        return true;
    }

    //transfer image file to byte array which is used to initiate the ImageIcon
    public byte[] fileToByte(File img) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[0];
        try {
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, "jpg", baos);
            bytes = baos.toByteArray();
            System.err.println(bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baos.close();
        }
        return bytes;
    }

    // used to check the picture and upload the picture
    private void uploadPicture(ActionEvent e) {
        // TODO add your code here
        //Jfilechooser to show the folder list
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        //check the postfix of the file
        FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(button3);
        //perform the upload action when it passes the check
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
            if (files == null || files.length == 0) {
                return;
            }
            File picture = chooser.getSelectedFile();
            String pictureName = picture.getName();
            String prefix = pictureName.substring(pictureName.lastIndexOf(".") + 1).toLowerCase();
            if (!(prefix.equals("jpg") || prefix.equals("png"))) {
                JOptionPane.showMessageDialog(new JDialog(), ":the picture should be .jpg or .png");
                return;
            }
            try {
                //assign the image to the model
                ImageIcon imageIcon = new ImageIcon(fileToByte(picture));
                //p.setPhoto(imageIcon);
                photo = imageIcon;
            }catch (Exception e1){
                System.out.println(e1.getMessage());
                }
            }
    }

    //display profile photo in the label
    private void displayImage(){
        int height = imageLabel.getHeight();
        int width  = imageLabel.getWidth();
        Image image = p.getPhoto().getImage();
        image = image.getScaledInstance(width,height,Image.SCALE_DEFAULT);
        ImageIcon scaleimage = new ImageIcon();
        scaleimage.setImage(image);
        imageLabel.setIcon(scaleimage);
    }

    //display text information
    private void display(ActionEvent e) {
            // TODO add your code here
        if (p.getPhoto()==null)
        {
            JOptionPane.showMessageDialog(new JDialog(), "no profile found");
            return;
        }
        displayImage();
        String FirstName = p.getFirstName();
        String LastName = p.getLastName();
        String DOB = p.getDOB();
        String Age = p.getAge();
        String number = p.getNumber();
        String Email = p.getEmail();
        String StreetLine1 = p.getStreetLine1();
        String StreetLine2 = p.getStreetLine2();
        String City = p.getCity();
        String Country = p.getCountry();
        String Affiliation= p.getAffiliation();
        String Major= p.getMajor();
        String Career=p.getCareer();
        String Degree1=p.getDegree1();
        String Degree1Startdate=p.getDegree1Startdate();
        String Degree1End=p.getDegree1End();
        String Degree2=p.getDegree2();
        String Degree2Startdate=p.getDegree2Startdate();
        String Degree2End=p.getDegree2End();

        profileLabel.setText("<html><body>First Name: "+ FirstName +"<br>"+
                "Last Name: "+LastName+"\n" +
                "DOB: "+ DOB+"<br>" +
                "Age: "+Age+"<br>" +
                "Telephone number: "+number+"<br>" +
                "Email: "+Email+"<br>" +
                "Street Line 1: "+ StreetLine1 +"<br>" +
                "Street Line 2: "+ StreetLine2 +"<br>" +
                "City: "+City+"<br>" +
                "Country: "+Country+"<br>" +
                "Affiliation:"+Affiliation+"<br>" +
                "Major: "+ Major+"<br>" +
                "Career: "+Career+"<br>" +
                "Degree 1: "+Degree1+"<br>" +
                "Degree 1 Start: "+Degree1Startdate+"<br>" +
                "Degree 1 End: "+Degree1End+"<br>" +
                "Degree 2: "+Degree2 +"<br>" +
                "Degree 2 Start: "+Degree2Startdate+"<br>" +
                "Degree 2 End"+Degree2End+"<body></html>");

    }

    private void addpicture(MouseEvent e) {
        // TODO add your code here
    }

    //make degree2 visibile
    private void degree2Visibile(ActionEvent e) {
        // TODO add your code here
        //check if the user completed the degree1 information
        if (!degree1check()){
            JOptionPane.showMessageDialog(this,"please complete degree1 first");
        }else {
            degree2(true);
            button1.setVisible(false);
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - yibin
        ResourceBundle bundle = ResourceBundle.getBundle("form");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label6 = new JLabel();
        label19 = new JLabel();
        label20 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        label16 = new JLabel();
        label17 = new JLabel();
        lastName = new JTextField();
        label5 = new JLabel();
        firstName = new JTextField();
        number = new JTextField();
        age = new JTextField();
        email = new JTextField();
        addline1 = new JTextField();
        addline2 = new JTextField();
        affiliation = new JTextField();
        major = new JTextField();
        career = new JTextField();
        degree1 = new JTextField();
        label18 = new JLabel();
        degree2 = new JTextField();
        city = new JTextField();
        country = new JTextField();
        DOBmonth = new JTextField();
        label21 = new JLabel();
        label22 = new JLabel();
        label23 = new JLabel();
        DOBday = new JTextField();
        DOByear = new JTextField();
        d1startmonth = new JTextField();
        d1startyear = new JTextField();
        label24 = new JLabel();
        d1endmonth = new JTextField();
        d1endyear = new JTextField();
        label29 = new JLabel();
        label25 = new JLabel();
        d2startmonth = new JTextField();
        d2startyear = new JTextField();
        label26 = new JLabel();
        label30 = new JLabel();
        d2endyear = new JTextField();
        d2endmonth = new JTextField();
        label31 = new JLabel();
        button3 = new JButton();
        button4 = new JButton();
        imageLabel = new JLabel();
        button5 = new JButton();
        profileLabel = new JLabel();
        button1 = new JButton();
        label14 = new JLabel();
        label15 = new JLabel();
        label27 = new JLabel();
        label28 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
            (0,0,0,0), "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e",javax.swing.border.TitledBorder.CENTER,javax.swing.border
            .TitledBorder.BOTTOM,new java.awt.Font("D\u0069al\u006fg",java.awt.Font.BOLD,12),java.awt
            .Color.red),dialogPane. getBorder()));dialogPane. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void
            propertyChange(java.beans.PropertyChangeEvent e){if("\u0062or\u0064er".equals(e.getPropertyName()))throw new RuntimeException()
            ;}});
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {

                //---- label1 ----
                label1.setText(bundle.getString("Profile.label1.text"));
                label1.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label2 ----
                label2.setText(bundle.getString("Profile.label2.text"));
                label2.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label3 ----
                label3.setText(bundle.getString("Profile.label3.text"));
                label3.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label4 ----
                label4.setText(bundle.getString("Profile.label4.text"));
                label4.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label6 ----
                label6.setText(bundle.getString("Profile.label6.text"));
                label6.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label19 ----
                label19.setText(bundle.getString("Profile.label19.text"));
                label19.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label20 ----
                label20.setText(bundle.getString("Profile.label20.text"));
                label20.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label7 ----
                label7.setText(bundle.getString("Profile.label7.text"));
                label7.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label8 ----
                label8.setText(bundle.getString("Profile.label8.text"));
                label8.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label9 ----
                label9.setText(bundle.getString("Profile.label9.text"));
                label9.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label10 ----
                label10.setText(bundle.getString("Profile.label10.text"));
                label10.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label11 ----
                label11.setText(bundle.getString("Profile.label11.text"));
                label11.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label12 ----
                label12.setText(bundle.getString("Profile.label12.text"));
                label12.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label13 ----
                label13.setText(bundle.getString("Profile.label13.text"));
                label13.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label16 ----
                label16.setText(bundle.getString("Profile.label16.text"));
                label16.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label17 ----
                label17.setText(bundle.getString("Profile.label17.text"));
                label17.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label5 ----
                label5.setText(bundle.getString("Profile.label5.text"));
                label5.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label18 ----
                label18.setText(bundle.getString("Profile.label18.text"));
                label18.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label21 ----
                label21.setText(bundle.getString("Profile.label21.text"));

                //---- label23 ----
                label23.setText(bundle.getString("Profile.label23.text"));

                //---- label24 ----
                label24.setText(bundle.getString("Profile.label24.text"));

                //---- label29 ----
                label29.setText(bundle.getString("Profile.label29.text"));

                //---- label25 ----
                label25.setText(bundle.getString("Profile.label25.text"));
                label25.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- label26 ----
                label26.setText(bundle.getString("Profile.label26.text"));

                //---- label30 ----
                label30.setText(bundle.getString("Profile.label30.text"));

                //---- label31 ----
                label31.setText(bundle.getString("Profile.label31.text"));
                label31.setHorizontalTextPosition(SwingConstants.CENTER);

                //---- button3 ----
                button3.setText(bundle.getString("Profile.button3.text"));
                button3.addActionListener(e -> submit(e));

                //---- button4 ----
                button4.setText(bundle.getString("Profile.button4.text"));
                button4.addActionListener(e -> display(e));

                //---- button5 ----
                button5.setText(bundle.getString("Profile.button5.text"));
                button5.addActionListener(e -> uploadPicture(e));
                button5.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        addpicture(e);
                    }
                });

                //---- button1 ----
                button1.setText(bundle.getString("Profile.button1.text"));
                button1.addActionListener(e -> degree2Visibile(e));

                //---- label14 ----
                label14.setText(bundle.getString("Profile.label14.text"));

                //---- label15 ----
                label15.setText(bundle.getString("Profile.label15.text"));

                //---- label27 ----
                label27.setText(bundle.getString("Profile.label27.text"));

                //---- label28 ----
                label28.setText(bundle.getString("Profile.label28.text"));

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addGroup(contentPanelLayout.createParallelGroup()
                                                    .addGroup(contentPanelLayout.createSequentialGroup()
                                                        .addGap(66, 66, 66)
                                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                                            .addComponent(label2)
                                                            .addComponent(label1)
                                                            .addComponent(label4)
                                                            .addComponent(label3)))
                                                    .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addGroup(contentPanelLayout.createParallelGroup()
                                                            .addComponent(label6, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(label5, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(label19, GroupLayout.Alignment.TRAILING)
                                                            .addComponent(label12, GroupLayout.Alignment.TRAILING))))
                                                .addComponent(label11)
                                                .addComponent(label10)
                                                .addComponent(label17))
                                            .addGap(18, 18, 18)
                                            .addGroup(contentPanelLayout.createParallelGroup()
                                                .addComponent(firstName)
                                                .addComponent(lastName)
                                                .addComponent(addline2)
                                                .addComponent(city)
                                                .addComponent(country)
                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(number, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                                            .addComponent(email, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(label23))
                                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                                            .addComponent(d1startmonth, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(d1startyear, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(label24))
                                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(age, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                                                                .addComponent(DOBmonth, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(DOBday, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(DOByear, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(label21))
                                                        .addComponent(addline1))
                                                    .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                                            .addGap(66, 66, 66)
                                            .addGroup(contentPanelLayout.createParallelGroup()
                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                    .addGroup(contentPanelLayout.createParallelGroup()
                                                        .addGroup(contentPanelLayout.createParallelGroup()
                                                            .addComponent(label20, GroupLayout.Alignment.TRAILING)
                                                            .addComponent(label7, GroupLayout.Alignment.TRAILING)
                                                            .addComponent(label16, GroupLayout.Alignment.TRAILING))
                                                        .addComponent(label18, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(contentPanelLayout.createParallelGroup()
                                                        .addComponent(career)
                                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                                            .addGroup(contentPanelLayout.createParallelGroup()
                                                                .addComponent(degree1, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(degree2, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                                    .addComponent(d1endmonth, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(18, 18, 18)
                                                                    .addComponent(d1endyear, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(label29)))
                                                            .addGap(0, 0, Short.MAX_VALUE))))
                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                    .addGroup(contentPanelLayout.createParallelGroup()
                                                        .addComponent(label9, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label8))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(contentPanelLayout.createParallelGroup()
                                                        .addComponent(affiliation)
                                                        .addComponent(major)))))
                                        .addGroup(GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                                            .addGroup(contentPanelLayout.createParallelGroup()
                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                    .addGap(67, 67, 67)
                                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                                            .addComponent(label25, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(d2startmonth, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(button3)
                                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                                            .addComponent(label31, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(d2endmonth, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(contentPanelLayout.createParallelGroup()
                                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                                            .addGap(68, 68, 68)
                                                            .addComponent(button4))
                                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                                            .addGap(18, 18, 18)
                                                            .addGroup(contentPanelLayout.createParallelGroup()
                                                                .addComponent(d2startyear, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(d2endyear, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(contentPanelLayout.createParallelGroup()
                                                                .addComponent(label26)
                                                                .addComponent(label30)))))
                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                    .addGap(143, 143, 143)
                                                    .addComponent(label13)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(button5)))
                                            .addGap(0, 0, Short.MAX_VALUE)))
                                    .addGroup(contentPanelLayout.createParallelGroup()
                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                            .addGap(27, 27, 27)
                                            .addComponent(label22))
                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(contentPanelLayout.createParallelGroup()
                                                .addComponent(label27)
                                                .addComponent(label15)
                                                .addComponent(label28)
                                                .addComponent(label14))))
                                    .addGap(55, 55, 55))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                    .addGap(120, 120, 120)))
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addComponent(profileLabel, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                                .addComponent(imageLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                );
                contentPanelLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {label1, label10, label11, label12, label16, label17, label19, label2, label20, label3, label4, label6, label7, label8});
                contentPanelLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {DOBday, DOBmonth});
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                            .addGap(71, 71, 71)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label1)
                                        .addComponent(firstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(8, 8, 8)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label2)
                                        .addComponent(lastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label3)
                                        .addComponent(label21)
                                        .addComponent(DOBmonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(DOBday, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(DOByear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(10, 10, 10)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label4)
                                        .addComponent(age, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(6, 6, 6)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label5)
                                        .addComponent(number, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(7, 7, 7)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label22)
                                        .addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label23))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(contentPanelLayout.createParallelGroup()
                                        .addComponent(label19, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addline1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(7, 7, 7)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label12)
                                        .addComponent(addline2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label14))
                                    .addGap(25, 25, 25)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label11)
                                        .addComponent(city, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(country, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label10))
                                    .addGap(25, 25, 25)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label9)
                                        .addComponent(affiliation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label15))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label8)
                                        .addComponent(major, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label27))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(career, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label7)
                                        .addComponent(label28))
                                    .addGap(20, 20, 20)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label20)
                                        .addComponent(degree1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label17)
                                        .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(d1startmonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(d1startyear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label24)))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label18)
                                        .addComponent(d1endmonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(d1endyear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label29))
                                    .addGap(3, 3, 3)
                                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(degree2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label16))
                                    .addGap(10, 10, 10)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label25)
                                        .addComponent(d2startmonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(d2startyear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label26))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label31)
                                        .addComponent(d2endmonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(d2endyear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label30))
                                    .addGap(35, 35, 35)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label13)
                                        .addComponent(button5))
                                    .addGap(40, 40, 40)
                                    .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(button3)
                                        .addComponent(button4)))
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(profileLabel, GroupLayout.PREFERRED_SIZE, 603, GroupLayout.PREFERRED_SIZE)))
                            .addGap(25, 25, 25))
                );
            }
            dialogPane.add(contentPanel, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - yibin
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label6;
    private JLabel label19;
    private JLabel label20;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label16;
    private JLabel label17;
    private JTextField lastName;
    private JLabel label5;
    private JTextField firstName;
    private JTextField number;
    private JTextField age;
    private JTextField email;
    private JTextField addline1;
    private JTextField addline2;
    private JTextField affiliation;
    private JTextField major;
    private JTextField career;
    private JTextField degree1;
    private JLabel label18;
    private JTextField degree2;
    private JTextField city;
    private JTextField country;
    private JTextField DOBmonth;
    private JLabel label21;
    private JLabel label22;
    private JLabel label23;
    private JTextField DOBday;
    private JTextField DOByear;
    private JTextField d1startmonth;
    private JTextField d1startyear;
    private JLabel label24;
    private JTextField d1endmonth;
    private JTextField d1endyear;
    private JLabel label29;
    private JLabel label25;
    private JTextField d2startmonth;
    private JTextField d2startyear;
    private JLabel label26;
    private JLabel label30;
    private JTextField d2endyear;
    private JTextField d2endmonth;
    private JLabel label31;
    private JButton button3;
    private JButton button4;
    private JLabel imageLabel;
    private JButton button5;
    private JLabel profileLabel;
    private JButton button1;
    private JLabel label14;
    private JLabel label15;
    private JLabel label27;
    private JLabel label28;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new Profile().setVisible(true);
    }
}

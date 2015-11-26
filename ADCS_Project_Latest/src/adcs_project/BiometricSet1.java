/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adcs_project;

/**
 *
 * @author Charles
 */
import java.awt.image.BufferedImage;
public class BiometricSet1 {
    
    private String studname;
    private String studmiddlename;
    private String Oname;
    private String mat;
    private String depart;
    private String fac;
    private String sex;
    private String level;
    private String pix;
    private String sig;
    private String fingerprnt;
    private String sess;
    
    //Staff information begins
    private String staffsurname;
    private String Firstname;
    private String staffmidname;
    private String address;
    private String title;
    private String staffNum;
    private String fone;
    private String state;
    private String lga;
    private String group;
    private String design;
    private String Phoneon;
    private String relation;
    private String bdate;
    private String kinsname;
    private String kinfname;
    private String kinadd;
    private String staffid,studentid;
    private String staffkinid,staffbioid,studentkinid,studentbioid;
    private String company_name,company_city,company_state;

    
    private BufferedImage pic;
    private BufferedImage si;
    private BufferedImage fin;
    private BufferedImage logo,front_id,back_id;
    
    BiometricSet1(){
     
    }
    
    
    
    BiometricSet1(String client_id,String firstname,String title,String middlename,String othernames,String gender,String faculty,String department,
            String level,String session,String address,String fone,String b_group,String state,String lga,String bdate){
        
      Matric(client_id); StudSurname(firstname);SetTitle(title);StudMiddlename(middlename);studOname(othernames);
      Sex(gender);Fac(faculty);Depart(department); 
      Level(level);SetSesion(session);
      Address(address);SetFone(fone);
       SetState(state);
     SetLga(lga);
     SetBloodGrp(b_group);
    SetBdate(bdate);
      //client_id,,,,,,,,,,,,,
      
    }
    
    
    BiometricSet1(String fn,String title,String sn,String mid,String add1,String fone,String depart,String lga,String stat,String stno,String bgroup,String design)
    {
      FirstName(fn);SetTitle(title);StaffSurname(sn);MiddleName(mid); 
      Address(add1);SetFone(fone);
      StaffNum(stno);Depart(depart);
      SetState(stat);
      SetLga(lga);
      SetBloodGrp(bgroup);
      SetDesign(design);
      
    }
    
    BiometricSet1(String m,BufferedImage m1,BufferedImage m2,BufferedImage m3)
    {
        StaffNum(m);
       SetBufferPix(m1);
       SetBufferSign(m2);
       SetBufferFinger(m3);
    }
    
    public BiometricSet1(String nam2,String city2,String sta2,BufferedImage m1,BufferedImage m2,BufferedImage m3){
        
    }
    
    public void StudSurname(String sname)
    {
       studname=sname; 
    }
    public void StudMiddlename(String smiddlename)
    {
       studmiddlename=smiddlename;
    }
     public void studOname(String onam)
    {
       Oname=onam; 
    }
    
    public void Matric(String mat2)
    {
        mat=mat2;
    }
    public void Depart(String depart2)
    {
        depart=depart2;
    }
    public void Fac(String fac3)
    {
        fac=fac3;
    }
    public void Sex(String sex3)
    {
        sex=sex3;
    }
    public void Level(String level2)
    {
      level=level2;  
    }
    
    public void SetPix(String pix)
    {
        this.pix=pix;
    }
     public void SetSig(String sig)
    {
        this.sig=sig;
    }
    public void SetFingerPrint(String fprnt)
    {
      this.fingerprnt=fprnt;
    }
    public void SetSesion(String see)
    {
        sess=see;
    }
    //staff set information Methods
    
    
     public void StaffSurname(String sname)
    {
       staffsurname=sname; 
    }
     public void FirstName(String first)
    {
       Firstname=first; 
    }
      public void MiddleName(String middle)
    {
       staffmidname=middle; 
    }
     public void Address(String add)
    {
       address=add; 
    }
      public void StaffNum(String snum)
    {
      staffNum=snum; 
    }
      
      public void SetFone(String fone2)
    {
      fone=fone2; 
    }
    
     public void SetState(String sta)
    {
      state=sta; 
    }
      public void SetLga(String ga)
    {
      lga=ga; 
    }
    
   public void SetBloodGrp(String grp)
    {
      group=grp; 
    }
   
    public void SetDesign(String des)
    {
     design=des; 
    }
     
    public void SetPhone(String phon)
    {
      Phoneon=phon;  
    }
    
    public void SetRelation(String re)
    {
        relation=re;
    }
    
    public void SetBdate(String b)
    {
        bdate=b;
    }
    
   public void SetKinSname(String s)
   {
       kinsname=s;
   }
    
   public void SetKinFname(String f)
   {
       kinfname=f;
   }
   
   public void SetKinaddress(String d)
   {
     kinadd=d;  
   }
    //set Buufered Image from database
    
    public void SetBufferPix(BufferedImage despix)
    {
     pic=despix; 
    }
    
    public void SetBufferSign(BufferedImage dissign)
    {
     si=dissign; 
    }
    public void SetBufferFinger(BufferedImage desfinger)
    {
     fin=desfinger; 
    }
     public void SetSelectedStaffid(String m)
 {
     staffid=m;
 }
   
 public void SetSelectedStudentid(String m2)
 {
   studentid=m2;  
 }  
 public void SetTitle(String m2)
 {
   title=m2;  
 }  
    
 
 
 
 
 //customization setup set methods
 
 public void SetCo_name(String c_name){company_name=c_name;}
 public void SetCo_City(String c_city){company_city=c_city;}
 public void SetLogo(BufferedImage log){logo=log;}
 public void SetFront_pix(BufferedImage fr){front_id=fr;}
 public void SetBack_pix(BufferedImage back){back_id=back;}
 
 //customization setup get methods
 
 public String getCo_name(){ return company_name;}
 public String getCo_City(){return company_city;}
 public BufferedImage getLogo(){return logo;}
 public BufferedImage getFront_pix(){return front_id;}
 public BufferedImage getBack_pix(){return back_id;}
 
 
 
public void SetSelectedStaffNextOfKinid(String d2){staffkinid=d2;}
public void SetSelectedStaffbioid(String d){staffbioid=d;}
public void SetSelectedStudentNextOfKinid(String d2){studentkinid=d2;}
public void SetSelectedStudentbioid(String d){studentbioid=d;}


 
 
 
    
    public BufferedImage getBufferedPix(){return  pic;}
    public BufferedImage getBufferedSig(){return si;}
    public BufferedImage getBufferedFingerPrint(){return fin;}
    
    
    
    
      
      
    public String getStudentSurname(){return studname;}
    public String getStudMiddlename(){return studmiddlename;}
    public String getStudentOname(){return Oname;}
    public String getMatric(){return mat;}
    public String getDepart(){return depart;}
    public String getFac(){return fac;}
    public String getSex(){return sex;}
    public String getLevel(){return level;}
    public String getSess(){return sess;}
    
    public String getPix(){return pix;}
    public String getSig(){return sig;}
    public String getFingerPrint(){return fingerprnt;}
   
    
    
    
    //Staff get Information start here
    
    
    
    
    public String getFirstName(){return Firstname;}
    public String getStaffSurname(){return staffsurname;}
    public String getMiddleName(){return staffmidname;}
    public String getAddress(){return address;}
    public String getStaffNum(){return staffNum;}
    public String getFone(){return fone;}
    public String getState(){return state;}
    public String getLga(){return lga;}
    public String getBloodgrp(){return group;}
    public String getDesign(){return design;}
    
    public String getPhone(){return Phoneon;}
    public String getRelation(){return relation;}
    public String getBdate(){return bdate;}
    public String getKinSname(){return  kinsname;}
    public String getKinFname(){return kinfname;}
    public String getKinAddress(){return kinadd;}
    public String getSelectedStaffid(){return staffid;}
    public String getSelectedStudentid(){ return studentid;}
    public String getTitle(){ return title;}
   
    
    
    
    public String getSelectedStaffkinid(){return staffkinid;}
    public String getSelectedStudentkinid(){ return studentkinid;}
    
    public String getSelectedStaffbioid(){return staffbioid;}
    public String getSelectedStudentbioid(){ return studentbioid;}
  
    
    }

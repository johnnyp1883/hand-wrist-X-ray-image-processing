/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hand.wrist.x.ray.image.processing;

/**
 *
 * @author Makoto
 */
public class BackGround2Value {
    private int[][] refImg,result;
    private int height,width;
    private int sizeMid;
    private int tmpH;
    private int tmpW;
    
    public BackGround2Value(int [][] Img,int height,int width,double mean,double SD){
        result=new int[height][width];
        this.height=height;
        this.width=width;
    
        sizeMid=1;
        tmpH=2*sizeMid+height;
        tmpW=2*sizeMid+width;
        Reflection ref=new Reflection(Img,tmpH,tmpW,sizeMid);
        refImg=ref.getImage();
        
        double lmean=0,lSD=0;
        for(int i=sizeMid;i<tmpH-sizeMid;i++)
            for(int j=sizeMid;j<tmpW-sizeMid;j++){
                 for(int k=-1;k<=+1;k++){
                       for(int l=-1;l<=+1;l++)
                          lmean+=refImg[i+k][j+l];}
                    lmean/=9;
        for(int k=-1;k<=+1;k++){
            for(int l=-1;l<=+1;l++)
                lSD+=(((double)refImg[i+k][j+l])-lmean)*(((double)refImg[i+k][j+l])-lmean);}
        lSD/=9;
        lSD=Math.sqrt(lSD); 
    if((lmean<=0.99999999*mean)&&(0.06*SD<=lSD)&&(lSD<=0.85*SD))
        
        result[i-sizeMid][j-sizeMid]=0;
    else
        result[i-sizeMid][j-sizeMid]=255;
          lmean=lSD=0;  
            }
                
       
    }
 /*   private void setValue(int sti,int stj){
    if(sti>height||stj>width)
         return;
    double lmean=0,lSD=0; 
    
    for(int i=-1;i<=+1;i++){
            for(int j=-1;j<=+1;j++)
                setValue(sti+i,sti+j);}
    }*/
    public int[][] getImg(){
    return result;
    }
    
}

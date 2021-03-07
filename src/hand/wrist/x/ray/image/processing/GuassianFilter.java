/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hand.wrist.x.ray.image.processing;


public class GuassianFilter {
    private int[] Vector;
    private int sizeMid;
    private int tmpH;
    private int tmpW;
    private int SumWeight;
    private int[][] result;
public GuassianFilter(Scale scale ,int[][] inputImage,int height,int width){
    result=new int[height][width];
    if(scale==Scale.three){
    Vector=new int[]{1,2,1};
    sizeMid=1;
    tmpH=2*sizeMid+height;
    tmpW=2*sizeMid+width;
    SumWeight=16;
    }
    else if(scale==Scale.five){
    Vector=new int[]{1,4,6,4,1};
    sizeMid=2;
    tmpH=2*sizeMid+height;
    tmpW=2*sizeMid+width;
    SumWeight=256;
    }
    else{
    Vector=new int[]{1,6,15,20,15,6,1};    
    sizeMid=3;
    tmpH=2*sizeMid+height;
    tmpW=2*sizeMid+width;
    SumWeight=4096;
     }
    Reflection ref=new Reflection(inputImage,tmpH,tmpW,sizeMid);
  
    int[][] refImg=ref.getImage();
    
    int tmp=0;
    for(int i=sizeMid;i<tmpH-sizeMid;i++)
        for(int j=sizeMid;j<tmpW-sizeMid;j++){
            for(int k=-sizeMid;k<=sizeMid;k++){
                for(int l=-sizeMid;l<=sizeMid;l++)
                   tmp+=refImg[i+k][j+l]*(Vector[k+sizeMid]*Vector[l+sizeMid]);} 
            result[i-sizeMid][j-sizeMid]=tmp/SumWeight;
            tmp=0;
            
                    }
    }
public int[][] getImg(){
return result;
}
    
}

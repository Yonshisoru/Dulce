/*123456789
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

public class CheckLogin {
    public static void main(String[] args){
        String eiei = "12.12";
        try{
            if((Double.valueOf(eiei)*100)%100==0){
            System.out.println("yes");
            }else{
                System.out.println("no");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}

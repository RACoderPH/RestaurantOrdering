import javax.swing.*;

public class RestaurantOrderingSystem {
    public static void main(String[] args) {
//         SplashScreen splash = new SplashScreen();
//        splash.setTitle("SplashScreen");
//             splash.setVisible(true);
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        
//              try{
//                   for(int i = 0; i <= 100; i++){
//                       Thread.sleep(40);
//                       splash.loadingnum.setText(Integer.toString(i) + "%");
//                       splash.loadingbar.setValue(i);
//                       
//                       if(i == 100){
//                          
//                          splash.setVisible(false);
//                          loginFrame.setVisible(true);
//                    
//                       }
//                   } 
//                }catch (Exception e){
//                    
//                }
        
    }

    // Method to validate login credentials (for demonstration purposes)
    public static boolean isValidLogin(String username, String password) {
        // Fake login credentials for demonstration purposes
        String validUsername = "admin";
        String validPassword = "password";
        return username.equals(validUsername) && password.equals(validPassword);
    }
}

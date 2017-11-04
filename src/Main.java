import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Main {


	public static void main(String[] args) {
		try{
			for( LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
				if( "Nimbus".equals(info.getName()) ){
					UIManager.setLookAndFeel( info.getClassName());
					break;
				}
			}
			new MainView();
		}
		catch(Exception e){
			System.out.println("Something bad happened");
		}
		
	}
}

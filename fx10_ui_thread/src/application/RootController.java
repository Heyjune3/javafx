package application;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class RootController implements Initializable{
	
	@FXML private Label lblTime;
	@FXML private Button btnStart, btnStop;
	
	// timer 종료 flag
	private boolean isRun;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btnStart.setOnAction(e->{
			isRun = true;
			Stage stage = (Stage)lblTime.getScene().getWindow();
			stage.setTitle("Timer");
			System.out.println(Thread.currentThread());
			System.out.println(Thread.currentThread().isDaemon());
			
			Thread t = new Thread(()->{
				
				LocalTime time = LocalTime.of(0, 0, 0);
				
				System.out.println(Thread.currentThread());
				while(isRun) {
					// 1000000000 10억분의 1초 nano times
					// 1000000    millis
					// 10000000   10 millis
					time = time.plusNanos(10000000);
					
					String data = time.format(
						DateTimeFormatter.ofPattern("mm:ss:SS")
					);
					// System.out.println(data);
					Platform.runLater(()->{
						lblTime.setText(data);
					});
					/*
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							lblTime.setText(data);
						}
					});
					*/
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {}
				}
			}); // end Thread Runnable run()
			t.setDaemon(true);
			t.start();
			
		}); // end btnStart
		
		btnStop.setOnAction(e->{
			isRun = false;
		}); // end btnStop
		
	} // end initialize
	
	
	
}















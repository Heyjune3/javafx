package chat_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ServerController implements Initializable{

	@FXML private TextArea displayText;
	@FXML private TextField txtPort;
	@FXML private Button btnStartStop;
	
	// Client Thread를 관리할 스레드 풀
	ExecutorService serverPool;
	// 운영체제에서 요청한 포트로 프로세스를 할당받아
	// client socket 연결관리 할 class
	ServerSocket server;
	// 연결된 client의 닉네임을 key값으로 서버에서 발신할 정보를 value로 저장하는 Map 객체
	// <Client ID, socket 출력 스트림>
	// 멀티 쓰레드 환경이기 때문에 Hashtable을 사용
	Hashtable<String, PrintWriter> clients;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnStartStop.setOnAction(e->{
			String text = btnStartStop.getText();
			// Start
			
			if(text.equals("_Start")) {
				startServer();
				btnStartStop.setText("S_top");
			}else {
				stopServer();
				btnStartStop.setText("_Start");
			}
		});
	}
	
	// 서버 실행 담당
	public void startServer(){
		// - UI Thread == 주문 접수 카운터 ( 한 고객이 주문을 완료하기 전까지 기다림)
		// 대기하는 고객이 많기 때문에 단일 주문에 너무 많은 시간을 소비할 수는 없듯이 UI Thread가 차단되면 어플이 응답하지 않을 수 있음
		// - Worker Thread == 주방 요리사 (카운터에서 주문을 받는 과정에 영향을 주지 않고 독립적으로 작업하여 여러 요리를 동시에 준비할 수 있음)
		/* == 요약하면, UI 스레드는 주문 접수 카운터와 같아서 사용자와 상호 작용하고 사용자 인터페이스를 관리하는 반면,
		 * 작업자 스레드는 주방의 요리사와 같아서 사용자 인터페이스의 응답성에 영향을 주지 않고 백그라운드 작업과 계산을 처리합니다.
		 * 이러한 작업을 분리하면 원활하고 효율적인 운영을 유지하는 데 도움이 됩니다.*/
		serverPool = Executors.newFixedThreadPool(50);
		clients = new Hashtable<>();
		
		String port = txtPort.getText().trim();
		for(char c : port.toCharArray()) {
			if(c < 48 || c > 57) {
				displayText.appendText("사용할 수 없는 PORT 번호입니다. \n");
				return;
			}
		}
		// parseInt == 기본 Int를 반환
		// valueOf == Int의 wrapper class 인 Integer를 반환
		// * wrapper 클래스는 기본 데이터 타입을 Object 로 변환
		int portNumber = Integer.parseInt(port);
		// 해당 포트 번호를 할당 받아서 client에 연결 요청을 수락할 수 있도록
		// serverSocket 설정
		// 0~1023 - 주요 통신을 위한 규약에 따라 이미 정해져 있는 포트
		// 1023 >= portNumber || 65535 < portNumber
		
		try {
			server = new ServerSocket(portNumber);
			
		} catch (IOException e) {
			// 포트 번호는 하나의 프로그램에서만 사용할 수 있다.
			displayText.appendText("이미 사용중인 포트 입니다. \n");
			stopServer();
			return;
		}
		
		// Thread Pool 에서 관리되는 Thread에서 필요한 작업을 전달.
		Runnable run = new Runnable() {

			@Override
			public void run() {
				// client 연결 요청 수락 후 연결 정보가 저장된 Socket 생성
				displayText.appendText("[ START SERVER ]");
				while(true) {
					try {
						// client 연결 대기
						printText("client 연결 대기중...");
						Socket client = server.accept();
						String address = client.getRemoteSocketAddress().toString();
						printText("[연결 수락 : "+address+"]");
						
						serverPool.submit(new ServerTask(client, ServerController.this));
					} catch (IOException e) {
						stopServer();
						break;
					}
				}
				
			}		
			
		};
		serverPool.submit(run);
	} // end start server
		
	
	// 서버 종료 자원 해제 담당
	public void stopServer(){
		
		if(clients != null && clients.isEmpty()) {
			for(PrintWriter p : clients.values()) {
				if(p != null) p.close();
			}
		}
		clients.clear();
		
		if(server != null && !server.isClosed()) {
			try {
				server.close();
			} catch (IOException e) {}
		}
		
		if(serverPool != null && !serverPool.isShutdown()) {
			serverPool.shutdownNow();
		}
		
		printText("[서버중지]");
		
	}
	
	// 작업 스레드에서 textArea에 출력 하는 UI 작업을 처리
	public void printText(String text) {
		
		Platform.runLater(()->{
			displayText.appendText(text+"\n");			
		});
		
	}
	
}










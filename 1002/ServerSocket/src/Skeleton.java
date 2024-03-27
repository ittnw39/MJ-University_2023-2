import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Skeleton {
	public Skeleton() {
		
	}
	
	public void process() {
		final int PORT = 12345; //서버 소켓을 만드려면 포트번호가 필요함 -> 바인딩()
		//서버 소켓은 자신의 네트워크 카드와 포트 넘버를 할당한다.
		//파일도 일종의 통신의 매거니즘, 시간적 통신 / 네트워크는 공간적 통신
		try {
			//서버 소켓 생성
			ServerSocket serverSocket = new ServerSocket(PORT);
			
			while(true) { // 서버 무한 루프
					
				//클라이언트 연결 대기
				System.out.println("서버가 시작되었습니다. 포트: " + PORT);
				Socket clientSocket = serverSocket.accept();
				System.out.println("클라이언트가 연결되었습니다.: " + clientSocket.getInetAddress());
				
				
				
				//세션 //클라이언트와 데이터를 주고받기 위한 입력 및 출력 스트림 생성, 인풋은 대부분 버퍼를 만든다.
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				
			
				String objectName = null;
				String methodName = null;
				String args = null;
				//클라이언트로부터 메시지 받기
				while((objectName = in.readLine()) != null) { //null이 아닐 때까지 읽기
					methodName = in.readLine();
					args = in.readLine();
					
					System.out.println("클라이언트로부터 받은 메시지: " + objectName + methodName + args);
					
					//클라이언트에게 응답 보내기 null string
					Control control = new Control();
					String userInfo = control.getUserInfo();
					
					out.println(userInfo); //쓰기, 에코 프린트(그대로 보내기)
					
				}
				//연결 종료
				clientSocket.close();
				System.out.println("클라이언트와 연결 종료: " + clientSocket.getInetAddress());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

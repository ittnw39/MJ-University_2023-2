import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Stub {
private String serverIP;
private int serverPort;

 public Stub(String serverIP, int serverPort) {
	 this.serverIP = serverIP;
	 this.serverPort = serverPort;
	 
 }
 
 public String request(String objectName, String methodName, String args){
		try {
			//서버에 연결
			Socket socket = new Socket(serverIP, serverPort);
			System.out.println("스텁이 스켈레톤에 연결되었습니다.");
			
			//서버와 데이터를 주고받기 위한 입력 및 출력 스트림 생성
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			//서버로 메시지 전송
			out.println(objectName);
			out.println(methodName);
			out.println(args);
			
			
			System.out.println("스텁이 서버로 보낸 메시지: " + objectName + methodName + args);
			
			//서버로부터 응답 받기
			String userInfo = in.readLine();
			System.out.println("서버로부터 받은 응답: " + userInfo);
			
			//연결 종료
			socket.close();
			
			return userInfo;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	 }
}

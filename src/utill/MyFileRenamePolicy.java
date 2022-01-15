package utill;

import java.io.File;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File arg0) {
		//lastIndexOf - 주어진 값과 일치하는 부분을 역순으로 탐색하여 최초로 마주치는 인덱스를 반환
		//일치하는 부분을 찾을 수 없으면 -1반환
		int pos = arg0.getName().lastIndexOf(".");
		String ext = "";
		if(pos != -1) {
			//substring - 문자열에서 특정 부분만 골라낼 떄 사용
			ext = arg0.getName().substring(pos);
		}
		return new File(arg0.getAbsolutePath().replace(arg0.getName(), "")+UUID.randomUUID()+ext);
	}
	
}

package vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Member {
	@NonNull
	private String id;
	@NonNull
	private String pwd;
	private String email;
	private String name;
}

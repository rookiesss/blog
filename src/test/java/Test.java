import com.java1234.entity.Blog;
import com.java1234.entity.BlogPage;
import com.java1234.service.BlogService;
import com.java1234.service.impl.BlogServiceImpl;
import org.apache.commons.lang.StringEscapeUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:applicationContext*.xml", "classpath:spring-mvc.xml"})
public class Test {
	@Autowired
	private BlogService blogService;

//	public static void main(String[] args) {
//		String html="<html>";
//		System.out.println(StringEscapeUtils.escapeHtml(html));
//	}
	@org.junit.Test
	public void test(){
		BlogPage blogPage = new BlogPage();
		ArrayList arrayList = new ArrayList();
		arrayList.add(1);
		blogPage.setStart(2);
		blogPage.setLimit(6);
		//blogPage.setTypeList(arrayList);
		//BlogService blogService = new BlogServiceImpl();
		List<Blog> blogList=blogService.query(blogPage);
		for (Blog blog:blogList
			 ) {
			System.out.println(blog.getTitle()+"///");
		}

	}
}

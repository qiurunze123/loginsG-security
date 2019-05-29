package com.loginsgeekq.validate.code;

import com.loginsgeekq.core.SecurityProperties;
import com.loginsgeekq.reflecttest.testI;
import com.loginsgeekq.validate.code.sms.SmsCodeGenerator;
import com.loginsgeekq.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

@RestController
public class ValidateCodeController  {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private SmsCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Autowired
    @Resource(name="Test1Impl2")
    private testI testCollect;

    @Autowired
    private Map<String, testI> aaa;

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request , HttpServletResponse response) throws IOException {

        ImageCode imageCode =  createImageCode(request);

        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"jpg",response.getOutputStream());
    }


    @GetMapping("/code/sms")
    public void createSmsCode(ServletWebRequest request, ServletWebRequest response) throws IOException, ServletRequestBindingException {

//        testCollect.create();

        ValidateCode validateCode = smsCodeGenerator.generate( request);
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
        sessionStrategy.setAttribute(new ServletWebRequest(request.getRequest()), SESSION_KEY, validateCode.getCode());
        smsCodeSender.send(mobile, validateCode.getCode());
    }


    private ImageCode createImageCode(HttpServletRequest request){

        int width = Integer.valueOf(securityProperties.getImageCodeProperties().getWidth());
        int height = Integer.valueOf(securityProperties.getImageCodeProperties().getHeight());
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        for (int i = 0; i < Integer.valueOf(securityProperties.getImageCodeProperties().getLength()); i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

        return new ImageCode(image, sRand,  Integer.valueOf(securityProperties.getImageCodeProperties().getExpireIn()));

    }

    /**
     * 生成随机背景条纹
     *
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}

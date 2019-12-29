package com.mettre.modulecommon.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.constant.CommonConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class JwtFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    public void destroy() {
        logger.info("哈哈哈哈哈哈哈 --  destroy");
        SecurityContextStore.clearContext();
        super.destroy();
    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;

        Result<Object> result = new Result<>();
//        SecretKey key = JwtUtils.generalKey();
        //客户端将token封装在请求头中，格式为（Bearer后加空格）：Authorization：Bearer +token
        final String authHeader = request.getHeader(CommonConstant.AUTHORITIES);
        if (authHeader == null || !authHeader.startsWith(CommonConstant.BEARER)) {

            result = Result.NotLoginEd();
        } else {
            try {
                //去除Bearer 后部分
                final String jwt = authHeader.substring(7);
                //解密token，拿到里面的对象claims
                final Claims claims = Jwts.parser().setSigningKey(CommonConstant.JWT_TOKEN).parseClaimsJws(jwt).getBody();
                //将对象传递给下一个请求
                AccessToken accessToken = new AccessToken(claims.getSubject());
                SecurityContextStore.setContext(accessToken);
                request.setAttribute(CommonConstant.CLAIMS, claims);
//                result.setCode(CommonConstant.ERROR);//未知错误
            } catch (Exception e) {
                result = Result.loginInvalid();
            }
        }

        if (CommonConstant.NOTLOGIN.equals(result.getCode())) {// 验证成功
            PrintWriter writer = null;
            OutputStreamWriter osw = null;
            try {
                osw = new OutputStreamWriter(res.getOutputStream(), "UTF-8");
                writer = new PrintWriter(osw, true);
                String jsonStr = new ObjectMapper().writeValueAsString(result);
                writer.write(jsonStr);
                writer.flush();
                writer.close();
                osw.close();
            } catch (UnsupportedEncodingException e) {
                logger.error("过滤器返回信息失败:" + e.getMessage(), e);
            } catch (IOException e) {
                logger.error("过滤器返回信息失败:" + e.getMessage(), e);
            } finally {
                if (null != writer) {
                    writer.close();
                }
                if (null != osw) {
                    osw.close();
                }
            }
            return;
        }

        chain.doFilter(req, res);
    }

}
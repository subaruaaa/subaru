package subaru.com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class MsMappingExceptionResolver extends SimpleMappingExceptionResolver {
    @Override
    protected ModelAndView doResolveException( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex ) {
        ex.printStackTrace();
        ModelAndView mv = super.doResolveException( request, response, handler, ex );
        mv.addObject( "callback", request.getParameter( "callback" ) );
        return mv;
    }
}

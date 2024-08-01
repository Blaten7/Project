package com.icia.recipe.home.restController;

import com.icia.recipe.home.dto.TradeDto;
import com.icia.recipe.home.service.TradeService;
import com.icia.recipe.management.dao.BoardDao;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.stream.Location;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@Slf4j
@EnableMethodSecurity(securedEnabled = true)
public class RestTradeController {
    @Autowired
    TradeService tSer;

    @GetMapping("/trade/dateSort")
    public List<TradeDto> tradeDateSort(){
        List<TradeDto> tDateList=tSer.tradeDateSort();
        log.info(""+tDateList);
        return tDateList;
    }

    @GetMapping("/trade/viewSort")
    public List<TradeDto> tradeViewSort(){
        List<TradeDto> tViewList=tSer.tradeViewSort();
        log.info(""+tViewList);
        return tViewList;
    }

    @GetMapping("/trade/countSort")
    public List<TradeDto> tradeCountSort(){
        List<TradeDto> tCountList=tSer.tradeCountSort();
        log.info(""+tCountList);
        return tCountList;
    }

    @PostMapping("/trade/recommend")
    public boolean tradeRecommend(TradeDto tDto,@RequestParam("t_num") Integer t_num,@AuthenticationPrincipal UserDetails userDetails){
        String m_id=userDetails.getUsername();
        tDto.setM_id(m_id);
        tDto.setT_num(t_num);
        boolean result=tSer.tradeRecommend(tDto);
        log.info("result: {}",result);
        if(result){
            log.info("성공햇슈ㅋㅋ");
            return true;
        }else{
            log.info("실패햇슈ㅠㅠ");
            return false;
        }
    }

    @PostMapping("/trade/messageSend")
    public boolean messageSend(){
        return true;
    }

    @PostMapping("/trade/accept")
    public boolean tradeExchange(@RequestParam("t_num") Integer t_num,
                                 @RequestParam("t_item") String t_item,
                                 @RequestParam("t_itemcount") Integer t_itemcount,
                                 TradeDto tDto){
        log.info("등장");
        tDto.setT_num(t_num);
        tDto.setT_item(t_item);
        tDto.setT_itemcount(t_itemcount);
        boolean result=tSer.tradeExcnage(tDto);
        if(result){
            log.info("교환 성공");
            return true;
        }else{
            log.info("교환 실패");
            return false;
        }
    }
    @PostMapping("/trade/refuse")
    public List<TradeDto> refuse(@RequestParam("t_num") Integer t_num, @RequestParam("tradesend") String tradesend,
                          @RequestParam("m_id") String m_id, TradeDto tDto){
        tDto.setT_num(t_num);
        tDto.setTradesend(tradesend);
        tDto.setM_id(m_id);
        boolean result = tSer.alertDelete(tDto);
        if(result){
            return tSer.alertList(tDto);
        }else{
            return null;
        }
    }
    @PostMapping("/alert/List")
    public String alertList(@AuthenticationPrincipal UserDetails userDetails,TradeDto tDto){
        log.info("일로오긴해?");
        String m_id=userDetails.getUsername();
        tDto.setM_id(m_id);
        List<TradeDto> alertList=tSer.alertList(tDto);
        StringBuilder sb=new StringBuilder();
        LocalDateTime now = LocalDateTime.now();
        // 초를 2자리로 포맷하기 위한 DateTimeFormatter 생성
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 포맷팅된 문자열 생성
        String formattedDate = now.format(formatter);
        // 초를 2자리로 잘라내기 (소수점 이하를 제외)
        String result = formattedDate.substring(0, 19); // "yyyy-MM-dd HH:mm:ss" 형식의 첫 19글자까지 추출
        for(TradeDto alert:alertList){
            sb.append("<div class='me-3' id='notification-"+alert.getT_num()+"-"+alert.getTradesend()+">")
                    .append("<i class='fas fa-file-alt text-white'></i>")
                    .append("</div>")
                    .append("</div>")
                    .append("<div id='socketAlertDiv'>")
                    .append("<span id='current-time' class='small text-gray-500'>")
                    .append(result)
                    .append("</span>$nbsp;")
                    .append("<button id='accept' onclick='("+alert.getT_num()+",\""+alert.getT_item()+"\", "+alert.getT_itemcount()+")'>수락</button>$nbsp;")
                    .append("<button id='refuse' onclick='refuse(" + alert.getT_num() + ", \"" + alert.getTradesend() + "\", \""+alert.getM_id()+"\")'>거절</button>")
                    .append("<a class='dropdown-item d-flex align-items-center' href='#'>")
                    .append("<div id='socketAlert' class='alert alert-warning' role='alert'>")
                    .append(alert.getTradesend() + "님이" + alert.getT_num() + "번 글에 " + alert.getT_item() + " " + alert.getT_itemcount() + alert.getT_unit() + "과(와)" + alert.getT_change() + "를 교환신청을 하였습니다.")
                    .append("</div>")
                    .append("</a>")
                    .append("</div>");
        }
        log.info(">>>>>>>>"+sb);
        return sb.toString();
    }
}

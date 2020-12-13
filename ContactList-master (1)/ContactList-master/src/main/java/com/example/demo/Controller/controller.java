package com.example.demo.Controller;


import com.example.demo.Dao.linkmanRepository;
import com.example.demo.Entity.linkman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class controller {
    //初始化数据库
    private linkmanRepository linkRepository;

    @Autowired
    public void setLinkRepository(linkmanRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    private List<linkman> linkmanList = new ArrayList<>();
    private int id = 0;

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login/input")
    public String login_post(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("User", "admin");
        return "redirect:/table";
    }

    @RequestMapping("/table")
    public ModelAndView table(Model model) {
        linkmanList = linkRepository.findAll();
        model.addAttribute("linkman", linkmanList);
        ModelAndView modelAndView = new ModelAndView("table", "model", model);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add(Model model) {
        model.addAttribute("linkman", new linkman());
        id += 1;
        ModelAndView modelAndView = new ModelAndView("add", "linkModel", model);
        return modelAndView;
    }

    @PostMapping(value = "/add/post")
    public String ADD(linkman contack) {
        linkmanList.add(contack);
        linkRepository.save(contack);
        System.out.println("id = " + contack.getId() + "添加数据库成功");
        return "redirect:/table";
    }

    @RequestMapping(value = "/table/edit/{edit_id}")
    public ModelAndView edit(@PathVariable("edit_id") int edit_id, Model model) {
        linkman edit_linkman = linkRepository.findById(edit_id).orElse(null);
        model.addAttribute("edit_linkman", edit_linkman);
        //System.out.print(edit_id);
        ModelAndView modelAndView = new ModelAndView("edit", "info", model);
        return modelAndView;
    }

    @RequestMapping(value = "edit/post/{edit_id}")
    public String updatePost(@PathVariable("edit_id") int id, linkman contack, Model model) {
        //System.out.println(contack.getName());
        int tempid = contack.getId();
        //这里传过来的contack的id会被重置，参数id不会
        contack.setId(id);
        linkRepository.save(contack);
        return "redirect:/table";
    }

    @PostMapping(value = "/table/delete/{delete_id}")
    public String deleteUser(@PathVariable("delete_id") int id) {
        System.out.println("id = " + String.valueOf(id) + "数据库删除成功");
        linkRepository.deleteById(id);
        return "redirect:/table";
    }

    @RequestMapping("/logout")
    public String logout(RedirectAttributes attributes, HttpServletRequest request) {
        HttpSession session = request.getSession();
        /*执行登出*/
        session.removeAttribute("User");
        return "redirect:/login";
    }

    //AJAX异步请求处理
    @RequestMapping("/add/phone")
    @ResponseBody
    public String Tel_ajax(String tel) {
        String msg = "";
        //System.out.println(11111);
        //System.out.println(tel);
        if (tel != null && tel != "") {
            Iterator<linkman> iter = linkmanList.iterator();
            int flag = 1;
            while (iter.hasNext()) {
                linkman link = iter.next();
                if (link.getTel().equals(tel)) {
                    flag = 0;
                    break;
                }
            }
            if (flag == 1)
                msg = "ok";
            else
                msg = "该电话号码已存在";
            //System.out.println(flag);
        }
        //System.out.println(msg);

        return msg;
    }
}

package kr.co.ymtech.bm.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import kr.co.ymtech.bm.controller.dto.UserManageDTO;
import kr.co.ymtech.bm.controller.dto.UserManagePageDTO;
import kr.co.ymtech.bm.service.IUserSystemService;
import kr.co.ymtech.bm.service.UserSystemService;

@RestController
@RequestMapping("/grade")
public class UserSystemController {

   private final IUserSystemService userSystemService;
   
   public UserSystemController(UserSystemService userSystemService){
      this.userSystemService = userSystemService;
   }
   
   /**
    * @Method getUserInfo 사용자 관리 시 유저 리스트를 가져오는 메소드
    *
    * @return 유저 데이터를 ResponseEntity를 통해 반환
    *
    * @author 박상현
    * @since 2024. 01. 18.
    */
   @GetMapping(value = "/manage")
   public ResponseEntity<UserManagePageDTO> getUserInfo(Integer pageNumber, Integer itemSize) {

      UserManagePageDTO userList = userSystemService.getUserInfo(pageNumber, itemSize);

      return new ResponseEntity<UserManagePageDTO>(userList, HttpStatus.OK);
   }
   
   /**
    * @Method updateGrade 사용자 권한 번호를 수정하는 메소드
    *
    * @return 유저 데이터를 수정하고 updateGrade에 담아 ResponseEntity를 통해 반환
    *
    * @author 박상현
    * @since 2024. 01. 18.
    */
   @PatchMapping(value = "/update")
   public ResponseEntity<Integer> updateGrade(@RequestBody UserManageDTO updateInfo) {

      Integer updateGrade = userSystemService.updateGrade(updateInfo);

      return new ResponseEntity<Integer>(updateGrade, HttpStatus.OK);
   }
   
   /**
    * @Method deleteUser 사용자를 삭제하는 메소드
    *
    * @return 유저 데이터를 삭제하고 deleteUser에 담아 ResponseEntity를 통해 반환
    *
    * @author 박상현
    * @since 2024. 01. 18.
    */
   @DeleteMapping(value = "/delete")
   public ResponseEntity<Integer> deleteUser(@RequestParam String id) {

      Integer deleteUser = userSystemService.deleteUser(id);

      return new ResponseEntity<Integer>(deleteUser, HttpStatus.OK);
   }
   
   
}
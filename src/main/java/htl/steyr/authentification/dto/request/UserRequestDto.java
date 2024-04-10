package htl.steyr.authentification.dto.request;

public record UserRequestDto(
        String username,
        String password,
        String email
){
}

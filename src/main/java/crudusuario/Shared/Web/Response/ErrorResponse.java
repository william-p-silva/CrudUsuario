package crudusuario.Shared.Web.Response;

public record ErrorResponse(
        Boolean success,
        int status,
        String message) {
}

public class Permission extends Entitlement {

    private String id;

    private String name;

    private String description;

    public Permission(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}

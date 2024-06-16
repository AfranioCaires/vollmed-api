package med.voll.api.physician;

public record PhysicianListData (Long id, String name, String email, String crm, Specialty specialty){
    public PhysicianListData(Physician physician){
        this(physician.getId(), physician.getName(), physician.getEmail(), physician.getCrm(), physician.getSpecialty());
    }
}

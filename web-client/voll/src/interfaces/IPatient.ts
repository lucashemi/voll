import IEntity from "./IEntity";

export default interface IPatient extends IEntity{
    ssn: string
    specialty: never
}
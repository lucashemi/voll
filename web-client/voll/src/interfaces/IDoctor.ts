import IEntity from "./IEntity";

export default interface IDoctor extends IEntity{
    specialty: string
    ssn: never
}
import { UserRoles } from '../helpers/UserRoles';

export class AddEditUsersForm {
  dni: string;
  name: string;
  email: string;
  pass1: string;
  pass2: string;
  role: UserRoles;
  institution_cue: string;

  constructor(
    {
      dni, email, institution_cue,
      name, pass1, pass2, role
    }: Partial<AddEditUsersForm> = {}
  ) {
    this.dni = dni || '';
    this.name = name || '';
    this.email = email || '';
    this.pass1 = pass1 || '';
    this.pass2 = pass2 || '';
    this.role = role || 'TEACHER';
    this.institution_cue = institution_cue || '';
  }
}

export function extractErrorMessage(errors: any): string {
    let msg = "";
    if (errors && typeof errors === 'object') {
      for (const field in errors) {
        if (errors[field] && errors[field].length > 0) {
          msg = errors[field][0];
          break;
        }
      }
    }
    return msg;
}
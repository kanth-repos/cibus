export const starUnchecked = () => `
  <span class="fa fa-star"></span>
`

export const starChecked = () => `
  <span class="fa fa-star style="color:orange;"></span>
`

export default function star(count) {
  let spans = starChecked().repeat(count);

  for(let i = 0; i < 5 - count; ++i) {
    spans += starUnchecked()
  }

  return `
    <div class="d-flex flex-row gap-2">
      ${spans}
    </div>
  `
}
